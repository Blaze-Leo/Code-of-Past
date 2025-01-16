{
 "cells": [
  {
   "cell_type": "code",
   "execution_count": 1,
   "metadata": {},
   "outputs": [
	{
	 "name": "stderr",
	 "output_type": "stream",
	 "text": [
	  "[nltk_data] Downloading package punkt to /home/anurag/nltk_data...\n",
	  "[nltk_data]   Package punkt is already up-to-date!\n"
	 ]
	},
	{
	 "data": {
	  "text/plain": [
	   "True"
	  ]
	 },
	 "execution_count": 1,
	 "metadata": {},
	 "output_type": "execute_result"
	}
   ],
   "source": [
	"import multiprocessing\n",
	"import math\n",
	"from wiki_dump_reader import Cleaner , iterate\n",
	"from tqdm import tqdm\n",
	"import numpy as np\n",
	"import multiprocessing\n",
	"import nltk\n",
	"from collections import Counter, defaultdict\n",
	"from nltk import ngrams, word_tokenize\n",
	"nltk.download('punkt')\n",
	"\n"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": 2,
   "metadata": {},
   "outputs": [],
   "source": [
	"def minimize_pre(so,de):\n",
	"\ti=100\n",
	"\twith open(so, 'r',encoding=\"utf-8\") as sor:\n",
	"\t\twith open(de, 'w',encoding=\"utf-8\") as des:\n",
	"\t\t\t\tfor line in sor:\n",
	"\t\t\t\t\tdes.write(line[:(len(line))//i])\n",
	"\n",
	"minimize_pre(\"ProcessedCorpus (copy).txt\", \"PreProcessed_Corpus.txt\")"
   ]
  },
  {
   "cell_type": "markdown",
   "metadata": {},
   "source": [
	"Decorator to calculate execution time of functions.\n"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": 3,
   "metadata": {},
   "outputs": [],
   "source": [
	"import time\n",
	"\n",
	"def calculate_time(func):\n",
	"\t\"\"\"\n",
	"\tDecorator function to calculate the execution time of another function.\n",
	"\n",
	"\tArgs:\n",
	"\t\tfunc (function): The function whose execution time is to be measured.\n",
	"\n",
	"\tReturns:\n",
	"\t\tfunction: A wrapped function that calculates and prints the execution time of the original function.\n",
	"\n",
	"\t\"\"\"\n",
	"\tdef wrapper(*args, **kwargs):\n",
	"\t\t\"\"\"\n",
	"\t\tCalculate the execution time of the decorated function.\n",
	"\n",
	"\t\tArgs:\n",
	"\t\t\t*args: Positional arguments to be passed to the decorated function.\n",
	"\t\t\t**kwargs: Keyword arguments to be passed to the decorated function.\n",
	"\n",
	"\t\tReturns:\n",
	"\t\t\tAny: The result of the decorated function.\n",
	"\t\t\n",
	"\t\t\"\"\"\n",
	"\t\t# Record the start time before executing the function\n",
	"\t\tstart_time = time.time()\n",
	"\t\t\n",
	"\t\t# Execute the decorated function\n",
	"\t\tresult = func(*args, **kwargs)\n",
	"\t\t\n",
	"\t\t# Record the end time after executing the function\n",
	"\t\tend_time = time.time()\n",
	"\t\t\n",
	"\t\t# Calculate and print the execution time\n",
	"\t\tprint(f\"Execution time of {func.__name__}: {end_time - start_time} seconds\")\n",
	"\t\t\n",
	"\t\t# Return the result of the decorated function\n",
	"\t\treturn result\n",
	"\treturn wrapper\n",
	"\n"
   ]
  },
  {
   "cell_type": "markdown",
   "metadata": {},
   "source": [
	"Decorator to print the memory location of a wrapped function.\n"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": 4,
   "metadata": {},
   "outputs": [],
   "source": [
	"def memory_location(func):\n",
	"\t\"\"\"\n",
	"\tA decorator function to print the memory location of a wrapped function.\n",
	"\n",
	"\tArgs:\n",
	"\t\tfunc (function): The function whose memory location is to be printed.\n",
	"\n",
	"\tReturns:\n",
	"\t\tfunction: A wrapped function that prints the memory location of the original function and then calls it.\n",
	"\n",
	"\t\"\"\"\n",
	"\tdef wrapper(*args, **kwargs):\n",
	"\t\t\"\"\"\n",
	"\t\tPrint the memory location of the decorated function and then call it.\n",
	"\n",
	"\t\tArgs:\n",
	"\t\t\t*args: Positional arguments to be passed to the decorated function.\n",
	"\t\t\t**kwargs: Keyword arguments to be passed to the decorated function.\n",
	"\n",
	"\t\tReturns:\n",
	"\t\t\tAny: The result of the decorated function.\n",
	"\t\t\n",
	"\t\t\"\"\"\n",
	"\t\tprint(f\"Memory location of {func.__name__}: {id(func)}\")\n",
	"\t\treturn func(*args, **kwargs)\n",
	"\treturn wrapper\n"
   ]
  },
  {
   "cell_type": "markdown",
   "metadata": {},
   "source": [
	"Decorator to calculate execution time and memory location of the create_corpus function.\n"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": 5,
   "metadata": {},
   "outputs": [],
   "source": [
	"@calculate_time\n",
	"@memory_location\n",
	"def create_corpus():\n",
	"\t\"\"\"\n",
	"\tCreate a corpus from a Wikipedia dump file in Hindi.\n",
	"\n",
	"\tThis function iterates over pages in a file named 'hiwiki-latest-pages-articles.xml',\n",
	"\tcleans the text, and writes it along with the title to a new file named 'Hindi_Corpus.txt'.\n",
	"\tIt also updates a progress bar using tqdm to show the processing progress.\n",
	"\n",
	"\tArgs:\n",
	"\t\tNone\n",
	"\n",
	"\tReturns:\n",
	"\t\tNone\n",
	"\n",
	"\tRaises:\n",
	"\t\tNone\n",
	"\n",
	"\t\"\"\"\n",
	"\tcorpus_file = 'Hindi_Corpus.txt'  # File to store the corpus\n",
	"\tcorpus_limit = 232729  # Limit for the number of pages in the corpus\n",
	"\tpage_count = 0  # Counter for the number of processed pages\n",
	"\tcleaner = Cleaner()  # Assuming Cleaner class is imported or defined elsewhere.\n",
	"\n",
	"\t# Open the output file for writing\n",
	"\twith open(corpus_file, 'w', encoding='utf-8') as output:\n",
	"\t\tpg_bar = tqdm(total=corpus_limit)  # Initialize the progress bar\n",
	"\t\t# Iterate over pages in 'hiwiki-latest-pages-articles.xml'\n",
	"\t\tfor title, text in iterate('hiwiki-latest-pages-articles.xml'):  # Assuming iterate function is defined elsewhere.\n",
	"\t\t\t# Clean the text\n",
	"\t\t\ttext = cleaner.clean_text(text)\n",
	"\t\t\tcleaned_text, _ = cleaner.build_links(text)\n",
	"\t\t\t# Write title and cleaned text to the corpus file\n",
	"\t\t\toutput.write(title + '\\n' + cleaned_text + '\\n')\n",
	"\t\t\tpage_count += 1\n",
	"\t\t\tif page_count % 1000 == 0:\n",
	"\t\t\t\tpg_bar.update(1000)  # Update progress bar every 1000 pages\n",
	"\t\tpg_bar.close()  # Close the progress bar\n",
	"\t\toutput.close()  # Close the output file\n",
	"\tprint(f\"\\nPage count = {page_count}\")  # Print total page count after processing\n",
	"\t\n",
	"\tcreate_corpus()\n",
	"\n"
   ]
  },
  {
   "cell_type": "markdown",
   "metadata": {},
   "source": [
	"Preprocess a source file by removing stop words and non-Hindi characters.\n"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": 6,
   "metadata": {},
   "outputs": [],
   "source": [
	"def remove_stop_words(string):\n",
	"\t\"\"\"\n",
	"\tRemove stop words from a given string.\n",
	"\n",
	"\tArgs:\n",
	"\t\tstring (str): Input string from which stop words are to be removed.\n",
	"\n",
	"\tReturns:\n",
	"\t\tstr: String with stop words removed.\n",
	"\t\"\"\"\n",
	"\t# Load stop words from file\n",
	"\twith open('stopwords.txt', 'r', encoding='utf-8') as stop:\n",
	"\t\tstop_words = stop.read().split()\n",
	"\n",
	"\t# Split the input string into words\n",
	"\twords = string.split()\n",
	"\n",
	"\t# Filter out stop words\n",
	"\tfiltered_words = [word for word in words if word not in stop_words]\n",
	"\n",
	"\t# Join the filtered words back into a string\n",
	"\treturn ' '.join(filtered_words)\n",
	"\n",
	"\n",
	"import re\n",
	"\n",
	"def remove_foreign(x):\n",
	"\t\"\"\"\n",
	"\tRemove non-Hindi characters from a given string.\n",
	"\n",
	"\tArgs:\n",
	"\t\tx (str): Input string from which non-Hindi characters are to be removed.\n",
	"\n",
	"\tReturns:\n",
	"\t\tstr: String containing only Hindi characters.\n",
	"\t\"\"\"\n",
	"\t# Split the input string into words\n",
	"\twords = x.split(' ')\n",
	"\n",
	"\t# Find Hindi characters using regex\n",
	"\thindi_chars = [(re.compile(r'[\\u0901-\\u0939\\u093C-\\u094D\\u0950-\\u0954\\u0958-\\u0963\\u097B-\\u097F]')).findall(s) for s in words]\n",
	"\n",
	"\t# Join the Hindi characters back into a string\n",
	"\thindi_string = ' '.join([''.join(chars) for chars in hindi_chars])\n",
	"\n",
	"\treturn hindi_string\n",
	"\n",
	"\n",
	"@calculate_time\n",
	"@memory_location\n",
	"def pre(source, destination):\n",
	"\t\"\"\"\n",
	"\tPreprocess a source file and write the preprocessed content to a destination file.\n",
	"\n",
	"\tArgs:\n",
	"\t\tsource (str): Path to the source file.\n",
	"\t\tdestination (str): Path to the destination file where preprocessed content will be written.\n",
	"\n",
	"\tReturns:\n",
	"\t\tNone\n",
	"\t\"\"\"\n",
	"\tline_count = 0\n",
	"\t# Open source file for reading and destination file for writing\n",
	"\twith open(source, 'r', encoding='utf-8') as input_file:\n",
	"\t\twith open(destination, 'w', encoding='utf-8') as output_file:\n",
	"\t\t\t# Initialize progress bar\n",
	"\t\t\tbar = tqdm(total=4400000)\n",
	"\t\t\t# Iterate over lines in the source file\n",
	"\t\t\tfor line in input_file:\n",
	"\t\t\t\t# Remove newline characters\n",
	"\t\t\t\tstring = line.strip('\\n')\n",
	"\t\t\t\t# Remove non-Hindi characters\n",
	"\t\t\t\tstring = remove_foreign(string)\n",
	"\t\t\t\t# Remove stop words\n",
	"\t\t\t\tstring = remove_stop_words(string)\n",
	"\t\t\t\t# Write preprocessed string to destination file\n",
	"\t\t\t\toutput_file.write(string)\n",
	"\t\t\t\t# Increment line count\n",
	"\t\t\t\tline_count += 1\n",
	"\t\t\t\t# Update progress bar every 10000 lines\n",
	"\t\t\t\tif line_count % 10000 == 0:\n",
	"\t\t\t\t\tbar.update(10000)\n",
	"\t\t\t# Close progress bar\n",
	"\t\t\tbar.close()\n",
	"\n"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": 7,
   "metadata": {},
   "outputs": [],
   "source": [
	"# pre('Hindi_Corpus.txt', 'PreProcessed_Corpus.txt')"
   ]
  },
  {
   "cell_type": "markdown",
   "metadata": {},
   "source": [
	"Generate vocabulary and distinct vocabulary lists from a preprocessed corpus file.\n"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": 8,
   "metadata": {},
   "outputs": [],
   "source": [
	"from collections import Counter\n",
	"\n",
	"const_vocab = 20  # Global constant for minimum word frequency\n",
	"\n",
	"def gen_vocab():\n",
	"\t\"\"\"\n",
	"\tGenerate a vocabulary list from the preprocessed corpus file.\n",
	"\n",
	"\tReads the text from 'PreProcessed_Corpus.txt' and returns it as a list of words.\n",
	"\n",
	"\tReturns:\n",
	"\t\tlist: A list of words extracted from the file 'PreProcessed_Corpus.txt'.\n",
	"\t\n",
	"\t\"\"\"\n",
	"\t# Read text from the preprocessed corpus file\n",
	"\twith open('PreProcessed_Corpus.txt', 'r', encoding='utf-8') as r:\n",
	"\t\ttext = r.read().split()\n",
	"\t\n",
	"\t# Count occurrences of each word\n",
	"\tword_counts = Counter(text)\n",
	"\t\n",
	"\t# Filter out words occurring less than const_vocab times\n",
	"\ttext = [word for word in tqdm(text) if word_counts[word] > const_vocab]  \n",
	"\t\n",
	"\treturn text\n",
	"\n",
	"def gen_distinct_vocab():\n",
	"\t\"\"\"\n",
	"\tGenerate a list of distinct vocabulary from the preprocessed corpus file.\n",
	"\n",
	"\tReads the text from 'PreProcessed_Corpus.txt', extracts distinct words, and returns them as a list.\n",
	"\tThis function computes the vocabulary size and stores it in a global variable 'vocabulary_size'.\n",
	"\n",
	"\tReturns:\n",
	"\t\tlist: A list of distinct words extracted from the file 'PreProcessed_Corpus.txt'.\n",
	"\t\n",
	"\t\"\"\"\n",
	"\t# Read text from the preprocessed corpus file\n",
	"\twith open('PreProcessed_Corpus.txt', 'r', encoding='utf-8') as r:\n",
	"\t\ttext = r.read().split()\n",
	"\t\t\n",
	"\t\t# Count occurrences of each word\n",
	"\t\tcounter = Counter(text)\n",
	"\t\t\n",
	"\t\t# Filter out words occurring less than const_vocab times\n",
	"\t\treduced_vocab = [item for item, count in counter.items() if count > const_vocab]\n",
	"\t\t\n",
	"\t\t# Convert to set to get distinct words\n",
	"\t\ttext_set = set(reduced_vocab)\n",
	"\t\t\n",
	"\t\t# Calculate vocabulary size and store it in a global variable\n",
	"\t\tglobal vocabulary_size\n",
	"\t\tvocabulary_size = len(text_set)\n",
	"\t\t\n",
	"\treturn list(text_set)\n"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": 9,
   "metadata": {},
   "outputs": [
	{
	 "name": "stderr",
	 "output_type": "stream",
	 "text": [
	  "100%|██████████| 383669/383669 [00:00<00:00, 4700565.28it/s]"
	 ]
	},
	{
	 "name": "stdout",
	 "output_type": "stream",
	 "text": [
	  "2277\n"
	 ]
	},
	{
	 "name": "stderr",
	 "output_type": "stream",
	 "text": [
	  "\n"
	 ]
	}
   ],
   "source": [
	"\n",
	"global words_list\n",
	"words_list=gen_vocab()\n",
	"distinct_vocab=gen_distinct_vocab()\n",
	"vocabulary_size = len(distinct_vocab)\n",
	"print((vocabulary_size))"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": 10,
   "metadata": {},
   "outputs": [],
   "source": [
	"\n",
	"def gen_grams():            \n",
	"\tglobal tokens\n",
	"\tglobal grams\n",
	"\ttokens = word_tokenize(' '.join(words_list))\n",
	"\tgrams = ngrams(tokens, 6)\n",
	"\treturn None \n",
	"\n",
	"gen_grams()"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": 11,
   "metadata": {},
   "outputs": [
	{
	 "name": "stdout",
	 "output_type": "stream",
	 "text": [
	  "Number of tokens is:  277760\n",
	  "Size of the vocabulary:  2277\n",
	  "First 1500 words of the Vocabulary:  ['मुख्य', 'कुमार', 'हिंदी', 'गीत', 'गीत', 'सदी', 'नागरिक', 'अधिकार', 'आंदोलन', 'प्रधान', 'स्वर', 'गीत', 'आमतौर', 'आई', 'माना', 'पहली', 'अधिकार', 'पूजा', 'विधि', 'पूजा', 'सनातन', 'हिन्दू', 'धर्म', 'उपासना', 'दैनिक', 'कर्म', 'विभिन्न', 'देवताओं', 'मन्त्र', 'पूजा', 'तीन', 'प्रकार', 'मंत्र', 'नाम', 'मंत्र', 'पौराणिक', 'मंत्र', 'वैदिक', 'मंत्र', 'देवता', 'नाम', 'पीछे', 'नमः', 'मंत्र', 'मंत्र', 'पुराणों', 'मंत्र', 'मंत्र', 'वेदों', 'मुख्यतः', 'छः', 'पांच', 'उपचार', 'उपचार', 'उपचार', 'उपचार', 'उपचार', 'सौ', 'उपचार', 'देवता', 'जिनकी', 'पूजा', 'निम्नलिखित', 'नाम', 'संस्कृत', 'पूजा', 'मुख्य', 'देवताओं', 'पूजा', 'नाम', 'मंत्र', 'ॐ', 'सिद्धि', 'विनायकाय', 'ॐ', 'ॐ', 'ॐ', 'ॐ', 'ॐ', 'श्री', 'नमः', 'ॐ', 'ॐ', 'श्री', 'नमः', 'ॐ', 'ॐ', 'श्री', 'ॐ', 'श्री', 'ॐ', 'नमः', 'ॐ', 'नमः', 'ॐ', 'ॐ', 'ॐ', 'मन्त्र', 'सिद्धि', 'विनायकाय', 'नमः', 'ॐ', 'सिद्धि', 'विनायकाय', 'नमः', 'ॐ', 'सिद्धि', 'विनायकाय', 'नमः', 'समर्पयामि', 'ॐ', 'सिद्धि', 'विनायकाय', 'नमः', 'समर्पयामि', 'ॐ', 'सिद्धि', 'विनायकाय', 'नमः', 'समर्पयामि', 'ॐ', 'सिद्धि', 'विनायकाय', 'नमः', 'समर्पयामि', 'ॐ', 'सिद्धि', 'विनायकाय', 'नमः', 'उप', 'समर्पयामि', 'ॐ', 'सिद्धि', 'विनायकाय', 'नमः', 'समर्पयामि', 'ॐ', 'सिद्धि', 'विनायकाय', 'नमः', 'वस्त्र', 'समर्पयामि', 'ॐ', 'सिद्धि', 'विनायकाय', 'नमः', 'समर्पयामि', 'ॐ', 'सिद्धि', 'विनायकाय', 'नमः', 'समर्पयामि', 'ॐ', 'सिद्धि', 'विनायकाय', 'नमः', 'समर्पयामि', 'ॐ', 'सिद्धि', 'विनायकाय', 'नमः', 'समर्पयामि', 'ॐ', 'सिद्धि', 'विनायकाय', 'नमः', 'ॐ', 'सिद्धि', 'विनायकाय', 'नमः', 'अंग', 'गणेश', 'पाँच', 'नाम', 'ॐ', 'नमः', 'ॐ', 'नमः', 'ॐ', 'नमः', 'ॐ', 'नमः', 'ॐ', 'नमः', 'ॐ', 'सिद्धि', 'विनायकाय', 'नमः', 'अंग', 'पत्र', 'गणेश', 'पाँच', 'नाम', 'ॐ', 'नमः', 'समर्पयामि', 'ॐ', 'नमः', 'समर्पयामि', 'ॐ', 'नमः', 'समर्पयामि', 'ॐ', 'नमः', 'समर्पयामि', 'ॐ', 'नमः', 'समर्पयामि', 'ॐ', 'सिद्धि', 'विनायकाय', 'नमः', 'पत्र', 'गणेश', 'पाँच', 'नाम', 'ॐ', 'नमः', 'समर्पयामि', 'ॐ', 'नमः', 'समर्पयामि', 'ॐ', 'नमः', 'समर्पयामि', 'ॐ', 'नमः', 'समर्पयामि', 'ॐ', 'नमः', 'समर्पयामि', 'ॐ', 'सिद्धि', 'विनायकाय', 'नमः', 'गणेश', 'नाम', 'नमः', 'ॐ', 'नमः', 'ॐ', 'नमः', 'ॐ', 'नमः', 'ॐ', 'नमः', 'ॐ', 'नमः', 'ॐ', 'नमः', 'ॐ', 'विनायकाय', 'नमः', 'ॐ', 'नमः', 'ॐ', 'नमः', 'ॐ', 'नमः', 'ॐ', 'नमः', 'ॐ', 'नमः', 'ॐ', 'नमः', 'ॐ', 'नमः', 'ॐ', 'सिद्धि', 'विनायकाय', 'नमः', 'ॐ', 'श्री', 'नमः', 'ॐ', 'नाम', 'सिद्धि', 'विनायकाय', 'नमः', 'सिद्धि', 'विनायकाय', 'नमः', 'ॐ', 'सिद्धि', 'विनायकाय', 'नमः', 'ॐ', 'सिद्धि', 'विनायकाय', 'नमः', 'समर्पयामि', 'ॐ', 'सिद्धि', 'विनायकाय', 'नमः', 'समर्पयामि', 'ॐ', 'सिद्धि', 'विनायकाय', 'नमः', 'समर्पयामि', 'ॐ', 'सिद्धि', 'विनायकाय', 'नमः', 'समर्पयामि', 'ॐ', 'सिद्धि', 'विनायकाय', 'नमः', 'समर्पयामि', 'ॐ', 'सिद्धि', 'विनायकाय', 'नमः', 'समस्त', 'समर्पयामि', 'ॐ', 'सिद्धि', 'विनायकाय', 'नमः', 'मंत्र', 'देव', 'न', 'न', 'न', 'ॐ', 'सिद्धि', 'विनायकाय', 'जिसके', 'रूप', 'मानक', 'हिन्दी', 'विश्व', 'प्रमुख', 'भाषा', 'भारत', 'राजभाषा', 'केन्द्रीय', 'स्तर', 'भारत', 'भाषा', 'अंग्रेजी', 'हिन्दुस्तानी', 'भाषा', 'रूप', 'संस्कृत', 'शब्दों', 'प्रयोग', 'शब्द', 'हिन्दी', 'संवैधानिक', 'रूप', 'भारत', 'राजभाषा', 'भारत', 'सबसे', 'बोली', 'वाली', 'भाषा', 'हिन्दी', 'भारत', 'राष्ट्रभाषा', 'नहीं', 'क्योंकि', 'भारत', 'संविधान', 'भाषा', 'दर्जा', 'नहीं', 'अनुसार', 'हिन्दी', 'विश्व', 'तीसरी', 'सबसे', 'बोली', 'वाली', 'भाषा', 'विश्व', 'आर्थिक', 'गणना', 'अनुसार', 'विश्व', 'दस', 'शक्तिशाली', 'जनगणना', 'भारतीय', 'जनसंख्या', 'हिन्दी', 'भारतीय', 'लोगों', 'हिन्दी', 'मूल', 'भाषा', 'घोषित', 'अतिरिक्त', 'भारत', 'पाकिस्तान', 'देशों', 'करोड़', 'लाख', 'लोगों', 'बोली', 'वाली', 'उर्दू', 'व्याकरण', 'आधार', 'हिन्दी', 'हिन्दुस्तानी', 'भाषा', 'रूप', 'विशाल', 'संख्या', 'लोग', 'हिन्दी', 'उर्दू', 'भारत', 'हिन्दी', 'विभिन्न', 'भारतीय', 'राज्यों', 'आधिकारिक', 'भाषाओं', 'क्षेत्र', 'बोलियों', 'उपयोग', 'लगभग', 'अरब', 'लोगों', 'दूसरी', 'भाषा', 'हिन्दी', 'भारत', 'सम्पर्क', 'भाषा', 'कार्य', 'करती', 'हद', 'भारत', 'सरल', 'रूप', 'भाषा', 'कभीकभी', 'हिन्दी', 'शब्द', 'प्रयोग', 'नौ', 'भारतीय', 'राज्यों', 'सन्दर्भ', 'उपयोग', 'जिनकी', 'आधिकारिक', 'भाषा', 'हिन्दी', 'हिन्दी', 'भाषी', 'बहुमत', 'अर्थात्', 'बिहार', 'छत्तीसगढ़', 'हरियाणा', 'हिमाचल', 'प्रदेश', 'झारखण्ड', 'मध्य', 'प्रदेश', 'राजस्थान', 'जम्मू', 'कश्मीर', 'उत्तर', 'प्रदेश', 'राष्ट्रीय', 'राजधानी', 'क्षेत्र', 'सम्पूर्ण', 'भारत', 'विविध', 'राज्यों', 'बोली', 'भारत', 'देशों', 'लोग', 'हिन्दी', 'लिखते', 'नेपाल', 'संयुक्त', 'अरब', 'हिन्दी', 'बोलियों', 'उपयोग', 'लोगों', 'बड़ी', 'संख्या', 'मौजूद', 'फरवरी', 'हिन्दी', 'न्यायालय', 'तीसरी', 'भाषा', 'रूप', 'भाषा', 'विद्यापति', 'सरस्वती', 'हिन्दुस्तानी', 'खड़ी', 'बोली', 'हिन्दी', 'नाम', 'विभिन्न', 'ऐतिहासिक', 'विभिन्न', 'प्रयुक्त', 'हिन्दी', 'यूरोपीय', 'अन्दर', 'आती', 'हिन्द', 'ईरानी', 'शाखा', 'हिन्द', 'आर्य', 'अन्तर्गत', 'संस्करण', 'रिपोर्ट', 'अनुसार', 'हिंदी', 'प्रथम', 'द्वितीय', 'भाषा', 'रूप', 'बोलने', 'लोगों', 'संख्या', 'आधार', 'हिंदी', 'विश्व', 'तीसरी', 'सबसे', 'बोली', 'वाली', 'शब्द', 'सम्बन्ध', 'संस्कृत', 'शब्द', 'सिन्धु', 'माना', 'सिन्धु', 'सिन्धु', 'नदी', 'आधार', 'आसपास', 'भूमि', 'सिन्धु', 'कहने', 'सिन्धु', 'शब्द', 'ईरानी', 'जाकर', 'हिन्दी', 'ईरानी', 'धीरेधीरे', 'भारत', 'भागों', 'शब्द', 'अर्थ', 'विस्तार', 'हिन्द', 'शब्द', 'भारत', 'ईरानी', 'बना', 'जिसका', 'अर्थ', 'यूनानी', 'शब्द', 'लैटिन', 'अंग्रेजी', 'शब्द', 'रूप', 'हिन्दी', 'भाषा', 'शब्द', 'प्राचीनतम', 'प्रयोग', 'मिलता', 'प्रमुख', 'उर्दू', 'लेखकों', 'सदी', 'सूचना', 'भाषा', 'हिंदी', 'रूप', 'महावीर', 'जैन', 'हिन्दी', 'उर्दू', 'शीर्षक', 'हिन्दी', 'विचार', 'प्राचीन', 'भाषा', 'ध्वनि', 'नहीं', 'बोली', 'बल्कि', 'संस्कृत', 'शब्द', 'शब्द', 'सिन्धु', 'नदी', 'पार', 'इलाके', 'प्राचीन', 'फ़ारसी', 'साहित्य', 'हिन्द', 'नामों', 'वस्तु', 'भाषा', 'विचार', 'रूप', 'जिसका', 'मतलब', 'हिन्द', 'हिन्द', 'शब्द', 'अरबी', 'ग्रीक', 'लैटिन', 'अंग्रेजी', 'इण्डिया', 'बन', 'भावना', 'अरबी', 'साधारण', 'रूप', 'इंडिया', 'मूल', 'अरबी', 'अरबी', 'फ़ारसी', 'साहित्य', 'भारत', 'हिन्द', 'बोली', 'वाली', 'भाषाओं', 'पद', 'उपयोग', 'भारत', 'आने', 'बोलने', 'वालों', 'हिन्दी', 'हिन्दी', 'प्रयोग', 'चारों', 'ओर', 'बोली', 'वाली', 'भाषा', 'उत्पत्ति', 'इतिहास', 'लगभग', 'वर्ष', 'पुराना', 'माना', 'भाषा', 'अन्तिम', 'शर्मा', 'पुरानी', 'हिन्दी', 'आधुनिक', 'भारतीय', 'भाषाओं', 'हिन्दी', 'स्वरूप', 'विकसित', 'ई॰', 'आसपास', 'स्वतन्त्र', 'सत्ता', 'परिचय', 'मिलने', 'लगा', 'भाषाएँ', 'साहित्यिक', 'प्रयोग', 'आ', 'भाषाएँ', 'विकसित', 'होकर', 'आधुनिक', 'भारतीय', 'आर्य', 'भाषाओं', 'रूप', 'रूप', 'वही', 'आधुनिक', 'बोलियों', 'सम्बन्ध', 'शब्द', 'चर्चा', 'वास्तव', 'देशी', 'शब्द', 'देशी', 'भाषा', 'भरत', 'शब्दों', 'संस्कृत', 'रूपों', 'भिन्न', 'शब्द', 'प्रचलित', 'शब्द', 'चले', 'आए', 'व्याकरण', 'नियमों', 'नहीं', 'करती', 'परन्तु', 'व्याकरण', 'विश्लेषण', 'पड़ता', 'संस्कृत', 'व्याकरण', 'लिखे', 'संस्कृत', 'प्राकृत', 'प्रकृति', 'अतः', 'शब्द', 'नियमों', 'न', 'आ', 'सके', 'देशी', 'संज्ञा', 'काल', 'हिन्दी', 'विकास', 'नयी', 'भारतीय', 'स्वतंत्रता', 'आन्दोलन', 'महात्मा', 'गाँधी', 'सहित', 'अनेक', 'नेताओं', 'भारतीय', 'एकता', 'हिन्दी', 'विकास', 'समर्थन', 'काशी', 'नागरी', 'सभा', 'हिन्दी', 'साहित्य', 'सम्मेलन', 'प्रयाग', 'प्रयासों', 'हिन्दी', 'नयी', 'ऊँचाई', 'भारत', 'स्वतन्त्रता', 'पश्चात', 'संविधान', 'हिन्दी', 'भारत', 'राजभाषा', 'अनुसार', 'हिन्दी', 'चार', 'प्रमुख', 'रूप', 'हिन्दी', 'हिन्दी', 'रूप', 'जिसकी', 'लिपि', 'देवनागरी', 'संस्कृत', 'भाषा', 'शब्द', 'जिन्होंने', 'फ़ारसी', 'अरबी', 'शब्दों', 'जगह', 'ले', 'ली', 'शुद्ध', 'हिन्दी', 'आधारित', 'दिल्ली', 'आसपास', 'क्षेत्रों', 'रूप', 'हैदराबाद', 'आसपास', 'शब्द', 'उर्दू', 'उर्दू', 'रूप', 'हिन्दी', 'रूप', 'देवनागरी', 'लिपि', 'बजाय', 'लिपि', 'लिखा', 'संस्कृत', 'शब्द', 'शब्द', 'उर्दू', 'मिलाकर', 'हिन्दुस्तानी', 'भाषा', 'हिन्दुस्तानी', 'हिन्दी', 'उर्दू', 'बोलचाल', 'भाषा', 'शुद्ध', 'संस्कृत', 'शुद्ध', 'शब्द', 'शब्द', 'उच्च', 'हिन्दी', 'भारतीय', 'संघ', 'राजभाषा', 'भारतीय', 'भारतीय', 'राज्यों', 'राजभाषा', 'उत्तर', 'प्रदेश', 'बिहार', 'झारखण्ड', 'मध्य', 'प्रदेश', 'हिमाचल', 'प्रदेश', 'छत्तीसगढ़', 'राजस्थान', 'हरियाणा', 'राज्यों', 'अतिरिक्त', 'महाराष्ट्र', 'गुजरात', 'पश्चिम', 'बंगाल', 'पंजाब', 'हिन्दी', 'भाषी', 'राज्यों', 'राज्यों', 'हिन्दी', 'बोलने', 'वालों', 'अच्छी', 'संख्या', 'उर्दू', 'पाकिस्तान', 'भारतीय', 'राज्य', 'जम्मू', 'कश्मीर', 'राजभाषा', 'अतिरिक्त', 'उत्तर', 'प्रदेश', 'बिहार', 'दिल्ली', 'द्वितीय', 'राजभाषा', 'लगभग', 'राज्यों', 'जिनकी', 'मुख्य', 'राजभाषा', 'हिन्दी', 'उर्दू', 'भाषा', 'हिन्दी', 'देवनागरी', 'लिपि', 'लिखी', 'शब्दावली', 'स्तर', 'संस्कृत', 'शब्दों', 'प्रयोग', 'करती', 'उर्दू', 'लिपि', 'लिखी', 'शब्दावली', 'स्तर', 'फ़ारसी', 'अरबी', 'भाषाओं', 'प्रभाव', 'हालाँकि', 'रूप', 'उर्दू', 'हिन्दी', 'अन्तर', 'नहीं', 'परन्तु', 'विशेष', 'क्षेत्रों', 'शब्दावली', 'स्रोत', 'लिखा', 'अन्तर', 'विशेष', 'उर्दू', 'अरबी', 'फ़ारसी', 'ली', 'प्रकार', 'फ़ारसी', 'अरबी', 'विशेष', 'प्रयोग', 'उर्दू', 'हिन्दी', 'आधिकारिक', 'प्राप्ति', 'हिन्दी', 'देवनागरी', 'दिशा', 'निम्नलिखित', 'क्षेत्रों', 'व्याकरण', 'मंत्रालय', 'केन्द्रीय', 'हिन्दी', 'देवनागरी', 'ढंग', 'देवनागरी', 'लिखने', 'क्षेत्र', 'विशाल', 'हिन्दी', 'अनेक', 'इनमें', 'अत्यन्त', 'उच्च', 'श्रेणी', 'साहित्य', 'रचना', 'ऐसी', 'बोलियों', 'ब्रजभाषा', 'अवधी', 'प्रमुख', 'हिन्दी', 'विविधता', 'शक्ति', 'हिन्दी', 'गहरा', 'हिन्दी', 'बोलियों', 'न', 'केवल', 'बड़ी', 'परम्परा', 'इतिहास', 'सभ्यता', 'स्वतन्त्रता', 'संग्राम', 'वर्तमान', 'विरुद्ध', 'रचना', 'संसार', 'बोलियों', 'प्रमुख', 'अवधी', 'ब्रजभाषा', 'भोजपुरी', 'किन्तु', 'हिन्दी', 'मुख्य', 'भेद', 'पश्चिमी', 'हिन्दी', 'देवनागरी', 'लिपि', 'लिखा', 'नागरी', 'नाम', 'जाना', 'देवनागरी', 'स्वर', 'व्यंजन', 'ओर', 'शब्दावली', 'मुख्यतः', 'शब्द', 'शब्द', 'संस्कृत', 'बिना', 'रूप', 'बदले', 'ले', 'अग्नि', 'परन्तु', 'हिन्दी', 'आने', 'शब्दों', 'संस्कृत', 'हिन्दी', 'केवल', 'शब्द', 'शब्द', 'जिनका', 'जन्म', 'संस्कृत', 'प्राकृत', 'उनमें', 'ऐतिहासिक', 'बदलाव', 'आया', 'शब्द', 'अर्थ', 'देश', 'बना', 'शब्द', 'अर्थ', 'न', 'विदेशी', 'भाषा', 'न', 'दूसरी', 'भाषा', 'शब्द', 'बना', 'शब्द', 'न', 'संस्कृत', 'न', 'शब्द', 'प्रदेश', 'क्षेत्र', 'लोगों', 'बोलचाल', 'बना', 'शब्द', 'अतिरिक्त', 'हिन्दी', 'शब्द', 'अरबी', 'फ़ारसी', 'अंग्रेजी', 'आये', 'विदेशी', 'अरबी', 'फ़ारसी', 'अंग्रेजी', 'शब्द', 'लगभग', 'पूर्ण', 'रूप', 'शब्दों', 'प्रयोग', 'लाया', 'शुद्ध', 'हिन्दी', 'लिपि', 'हिन्दी', 'आधुनिक', 'हिन्दी', 'हिन्दी', 'संस्कृत', 'स्वर', 'उच्चारण', 'संस्कृत', 'आधुनिक', 'हिन्दी', 'वर्ण', 'स्वर', 'चंद्र', 'प्रयोग', 'ना', 'रूप', 'स्वर', 'माना', 'स्वर', 'ना', 'व्यंजन', 'अतिरिक्त', 'व्यंजन', 'जिसका', 'प्रयोग', 'हिन्दी', 'नहीं', 'मराठी', 'वैदिक', 'संस्कृत', 'उच्चारण', 'ओर', 'जैसी', 'शुक्ल', 'यजुर्वेद', 'शाखा', 'उच्चारण', 'ख', 'आधुनिक', 'हिन्दी', 'उच्चारण', 'पूरी', 'उच्चारण', 'कभीकभी', 'यानी', 'परन्तु', 'शुद्ध', 'उच्चारण', 'लगा', 'न', 'स्वर', 'मुख्यत', 'अरबी', 'फ़ारसी', 'भाषाओं', 'शब्दों', 'मूल', 'उच्चारण', 'स्रोत', 'संस्कृत', 'नहीं', 'देवनागरी', 'लिपि', 'सबसे', 'देवनागरी', 'वर्ण', 'उच्चारण', 'उदाहरण', 'भाषाओं', 'हिन्दी', 'वाला', 'हिन्दी', 'लिंग', 'वस्तुओं', 'लिंग', 'भाषा', 'क्रिया', 'रूप', 'लिंग', 'निर्भर', 'हिन्दी', 'क्रिया', 'प्रभावित', 'जनगणना', 'भारतीय', 'आबादी', 'हिन्दी', 'भारतीय', 'लोगों', 'हिन्दी', 'मूल', 'भाषा', 'घोषित', 'भारत', 'हिन्दी', 'बोलने', 'संयुक्त', 'राज्य', 'अमेरिका', 'दक्षिण', 'नेपाल', 'लाख', 'जर्मनी', 'हिन्दी', 'चौथी', 'सर्वाधिक', 'बोली', 'वाली', 'मध्य', 'परस्पर', 'बनने', 'वाली', 'भाषा', 'सम्पर्क', 'भाषा', 'राष्ट्रीय', 'स्वरूप', 'हिन्दी', 'भारत', 'सम्पर्क', 'भाषा', 'सीमित', 'रूप', 'भाषा', 'रूप', 'हिन्दी', 'व्यवहार', 'भिन्न', 'परस्पर', 'सम्पूर्ण', 'भारतवर्ष', 'बोली', 'वाली', 'कारण', 'हिन्दी', 'राजभाषा', 'देश', 'जोड़ने', 'वाली', 'सम्पर्क', 'भारत', 'राजभाषा', 'सितम्बर', 'हिन्दी', 'भारत', 'राजभाषा', 'रूप', 'स्वतंत्रता', 'लोग', 'हिन्दी', 'राष्ट्रभाषा', 'आये', 'राष्ट्रभाषा', 'प्रचार', 'समिति', 'महाराष्ट्र', 'राष्ट्रभाषा', 'सभा', 'पुणे', 'किन्तु', 'भारतीय', 'संविधान', 'राष्ट्रभाषा', 'उल्लेख', 'नहीं', 'दृष्टि', 'हिन्दी', 'राष्ट्रभाषा', 'कहने', 'अर्थ', 'राष्ट्रभाषा', 'कहने', 'महात्मा', 'गांधी', 'जिन्होंने', 'मार्च', 'हिन्दी', 'साहित्य', 'सम्मेलन', 'उन्होंने', 'सार्वजनिक', 'पहली', 'हिन्दी', 'भारत', 'राष्ट्रभाषा', 'दर्जा', 'राष्ट्रभाषा', 'बिना', 'राष्ट्र', 'हिन्दी', 'भाषा', 'प्रश्न', 'प्रश्न', 'हिन्द', 'हिन्दुस्तानी', 'अभियान', 'गीत', 'कदम', 'कदम', 'भाषा', 'परन्तु', 'सुभाष', 'चन्द्र', 'बोस', 'हिन्दुस्तानी', 'भाषा', 'नहीं', 'अतः', 'बिना', 'कठिन', 'संस्कृत', 'शब्दावली', 'भारत', 'अनेक', 'निवास', 'करती', 'जिनकी', 'भाषाएँ', 'इनमें', 'जयन्तिया', 'गारो', 'खासी', 'प्रमुख', 'पूर्वोत्तर', 'भाषाओं', 'केवल', 'असमिया', 'भारतीय', 'संविधान', 'आठवीं', 'अनुसूची', 'मिला', 'राज्यों', 'हिन्दी', 'भाषा', 'प्रयोग', 'प्रवासी', 'हिन्दी', 'हिन्दी', 'औपचारिक', 'रूप', 'प्रवेश', 'वर्ष', 'महात्मा', 'गांधी', 'भारतीय', 'सभा', 'स्थापना', 'हेतु', 'असम', 'वैष्णव', 'स्वतन्त्रता', 'देव', 'गांधी', 'जी', 'होकर', 'बाबा', 'दास', 'हिन्दी', 'रूप', 'असम', 'वर्ष', 'असम', 'हिन्दी', 'प्रचार', 'समिति', 'स्थापना', 'गुवाहाटी', 'समिति', 'असम', 'राष्ट्रभाषा', 'प्रचार', 'समिति', 'आम', 'लोगों', 'हिन्दी', 'भाषा', 'साहित्य', 'हेतु', 'आयोजन', 'समिति', 'भारत', 'हिन्दी', 'स्थिति', 'सही', 'दिशा', 'बढ़', 'आजकल', 'अरुणाचल', 'प्रदेश', 'पैमाने', 'हिन्दी', 'बोली', 'लगी', 'हिन्दी', 'टीवी', 'सिनेमा', 'विद्यालय', 'महाविद्यालय', 'उच्च', 'शिक्षा', 'हिन्दी', 'भाषा', 'प्रयोग', 'पूर्व', 'संख्या', 'दृष्टि', 'विश्व', 'सर्वाधिक', 'बोली', 'वाली', 'भाषाओं', 'मिलते', 'उनमें', 'हिन्दी', 'तीसरा', 'सन्', 'ऑफ़', 'इण्डिया', 'भारतीय', 'भाषाओं', 'विश्लेषण', 'ग्रन्थ', 'प्रकाशित', 'संसार', 'भाषाओं', 'रिपोर्ट', 'तैयार', 'यूनेस्को', 'सन्', 'यूनेस्को', 'आधार', 'भारत', 'सरकार', 'केन्द्रीय', 'हिन्दी', 'संस्थान', 'तत्कालीन', 'महावीर', 'जैन', 'विस्तृत', 'रिपोर्ट', 'विश्व', 'स्तर', 'संख्या', 'दृष्टि', 'संसार', 'भाषाओं', 'चीनी', 'भाषा', 'हिन्दी', 'चीनी', 'भाषा', 'बोलने', 'वालों', 'संख्या', 'हिन्दी', 'भाषा', 'किन्तु', 'चीनी', 'भाषा', 'प्रयोग', 'क्षेत्र', 'हिन्दी', 'अपेक्षा', 'सीमित', 'अंग्रेजी', 'भाषा', 'प्रयोग', 'क्षेत्र', 'हिन्दी', 'अपेक्षा', 'किन्तु', 'संख्या', 'अंग्रेजी', 'बनने', 'गुण', 'हिन्दी', 'सदी', 'अन्तिम', 'दशकों', 'हिन्दी', 'अन्तरराष्ट्रीय', 'विकास', 'तेजी', 'हिन्दी', 'एशिया', 'व्यापारिक', 'धीरेधीरे', 'स्वरूप', 'भविष्य', 'अग्रणी', 'भाषा', 'रूप', 'स्थापित', 'संगीत', 'सिनेमा', 'बाजार', 'क्षेत्र', 'हिन्दी', 'तेजी', 'भाषा', 'विश्व', 'लगभग', 'विश्वविद्यालय', 'स्तर', 'शोध', 'स्तर', 'हिन्दी', 'व्यवस्था', 'लगभग', 'नियमित', 'रूप', 'हिन्दी', 'प्रकाशित', 'सहित', 'अनेक', 'देश', 'हिन्दी']\n"
	 ]
	}
   ],
   "source": [
	"print(\"Number of tokens is: \", len(tokens))\n",
	"print(\"Size of the vocabulary: \", vocabulary_size)\n",
	"print(\"First 1500 words of the Vocabulary: \", words_list[:1500])\n"
   ]
  },
  {
   "cell_type": "markdown",
   "metadata": {},
   "source": [
	"Create co-occurrence matrix based on grams with timing and memory measurement.\n"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": 12,
   "metadata": {},
   "outputs": [
	{
	 "name": "stdout",
	 "output_type": "stream",
	 "text": [
	  "Memory location of create_matrix: 128081886843168\n"
	 ]
	},
	{
	 "name": "stderr",
	 "output_type": "stream",
	 "text": [
	  "277755it [01:00, 4592.81it/s]"
	 ]
	},
	{
	 "name": "stdout",
	 "output_type": "stream",
	 "text": [
	  "Execution time of wrapper: 60.477678537368774 seconds\n"
	 ]
	},
	{
	 "name": "stderr",
	 "output_type": "stream",
	 "text": [
	  "\n"
	 ]
	}
   ],
   "source": [
	"\n",
	"cocrmat = np.zeros((len(distinct_vocab),len(distinct_vocab)),dtype=np.int64)\n",
	"\n",
	"@calculate_time\n",
	"@memory_location\n",
	"def create_matrix():\n",
	"\t\n",
	"\tramp=[0,1,2,3,4,5]\n",
	"\n",
	"\tfor gram in tqdm(grams):\n",
	"\n",
	"\t\tx=list(gram)\n",
	"\n",
	"\t\tif x[0] in distinct_vocab:\n",
	"\t\t\tfor i in range(1,len(x)):\n",
	"\t\t\t\tif x[i] in distinct_vocab:\n",
	"\t\t\t\t\tcocrmat[distinct_vocab.index(x[0])][distinct_vocab.index(x[i])] += 5-ramp[i]\n",
	"\t\t\t\t\t\n",
	"\t\tx.reverse()\n",
	"\t\n",
	"\t\tif x[0] in distinct_vocab:\n",
	"\t\t\tfor i in range(1,len(x)):\n",
	"\t\t\t\tif x[i] in distinct_vocab:\n",
	"\t\t\t\t\tcocrmat[distinct_vocab.index(x[0])][distinct_vocab.index(x[i])] += 5-ramp[i]\n",
	"\t\t\n",
	"create_matrix()\n"
   ]
  },
  {
   "cell_type": "markdown",
   "metadata": {},
   "source": [
	"Calculate probability and generate PPMI matrix with timing and memory measurement.\n"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": 13,
   "metadata": {},
   "outputs": [],
   "source": [
	"\n",
	"@calculate_time\n",
	"@memory_location\n",
	"def calculate_probability(matrix):\n",
	"\t\n",
	"\t# Calculate row sums and column sums\n",
	"\trow_sums = np.sum(matrix, axis=1)\n",
	"\tcol_sums = np.sum(matrix, axis=0)\n",
	"\n",
	"\t# Total co-occurrences\n",
	"\tN = np.sum(matrix)\n",
	"\n",
	"\tif(N==0):\n",
	"\t\treturn 0,0\n",
	"\n",
	"\t# Calculate pi and pj\n",
	"\tpI = row_sums / N\n",
	"\tpJ = col_sums / N\n",
	"\n",
	"\tglobal pi,pj\n",
	"\n",
	"\tpi,pj=pI,pJ\n",
	" \n",
	" \n",
	"@calculate_time\n",
	"@memory_location\n",
	"def gen_ppmi_matrix(matrix):\n",
	"\tco_occurrence_matrix=matrix\n",
	" \n",
	"\tppmi = np.zeros((len(distinct_vocab), len(distinct_vocab)))\n",
	"\n",
	"\trow_sums = np.sum(co_occurrence_matrix, axis=1)\n",
	"\tfor i in tqdm(range(len(distinct_vocab))):\n",
	"\t\tfor j in range(len(distinct_vocab)):\n",
	"\n",
	"\t\t\tif (row_sums[i] == 0 or co_occurrence_matrix[i][j] == 0):\n",
	"\t\t\t\tppmi[i][j] = 0\n",
	"\t\t\t\tcontinue\n",
	"\t\t\tpij = ((co_occurrence_matrix[i][j]))/row_sums[i]\n",
	"\t\t\tppmi[i][j] = max(0, math.log2(pij/(pi[i]*pj[j])))\n",
	"\n",
	"\treturn ppmi\n"
   ]
  },
  {
   "cell_type": "markdown",
   "metadata": {},
   "source": [
	"Calculate probability and generate PPMI matrix.\n"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": 14,
   "metadata": {},
   "outputs": [
	{
	 "name": "stdout",
	 "output_type": "stream",
	 "text": [
	  "Memory location of calculate_probability: 128081886843488\n",
	  "Execution time of wrapper: 0.008103609085083008 seconds\n",
	  "Memory location of gen_ppmi_matrix: 128081886844128\n"
	 ]
	},
	{
	 "name": "stderr",
	 "output_type": "stream",
	 "text": [
	  "  0%|          | 0/2277 [00:00<?, ?it/s]"
	 ]
	},
	{
	 "name": "stderr",
	 "output_type": "stream",
	 "text": [
	  "100%|██████████| 2277/2277 [00:01<00:00, 1350.73it/s]"
	 ]
	},
	{
	 "name": "stdout",
	 "output_type": "stream",
	 "text": [
	  "Execution time of wrapper: 1.689279317855835 seconds\n"
	 ]
	},
	{
	 "name": "stderr",
	 "output_type": "stream",
	 "text": [
	  "\n"
	 ]
	}
   ],
   "source": [
	"calculate_probability(cocrmat)\n",
	"ppmi_matrix=gen_ppmi_matrix(cocrmat)\n"
   ]
  },
  {
   "cell_type": "markdown",
   "metadata": {},
   "source": [
	"Script to find the most common elements in a list.\n"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": 15,
   "metadata": {},
   "outputs": [
	{
	 "name": "stderr",
	 "output_type": "stream",
	 "text": [
	  "100%|██████████| 383669/383669 [00:00<00:00, 7408815.77it/s]\n"
	 ]
	}
   ],
   "source": [
	"from collections import Counter\n",
	"\n",
	"my_list = gen_vocab()\n",
	"counter = Counter(my_list)\n",
	"common_elements = counter.most_common(10)\n",
	"most_common_elements=[a for (a,_) in common_elements]"
   ]
  },
  {
   "cell_type": "markdown",
   "metadata": {},
   "source": [
	"Script to find nearest neighbors for each noun in a given list.\n"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": 16,
   "metadata": {},
   "outputs": [
	{
	 "name": "stdout",
	 "output_type": "stream",
	 "text": [
	  "Memory location of nearest_seq: 128081886850368\n"
	 ]
	},
	{
	 "name": "stderr",
	 "output_type": "stream",
	 "text": [
	  "100%|██████████| 2276/2276 [00:01<00:00, 1501.81it/s]\n",
	  "100%|██████████| 2276/2276 [00:01<00:00, 1491.91it/s]\n",
	  "100%|██████████| 2276/2276 [00:01<00:00, 1458.55it/s]\n",
	  "100%|██████████| 2276/2276 [00:01<00:00, 1398.65it/s]\n",
	  "100%|██████████| 2276/2276 [00:01<00:00, 1447.58it/s]\n",
	  "100%|██████████| 2276/2276 [00:01<00:00, 1456.81it/s]\n",
	  "100%|██████████| 2276/2276 [00:01<00:00, 1500.74it/s]\n",
	  "100%|██████████| 2276/2276 [00:01<00:00, 1526.71it/s]\n",
	  "100%|██████████| 2276/2276 [00:01<00:00, 1438.67it/s]\n",
	  "100%|██████████| 2276/2276 [00:01<00:00, 1440.73it/s]\n",
	  "100%|██████████| 10/10 [00:15<00:00,  1.56s/it]"
	 ]
	},
	{
	 "name": "stdout",
	 "output_type": "stream",
	 "text": [
	  "Execution time of wrapper: 15.629382610321045 seconds\n",
	  "Words similar to  बीजिंग  :\n",
	  "   एन्साइक्लोपीडिया  :  0.37147304915123436\n",
	  "   ब्रिटेनिका  :  0.36400208114269533\n",
	  "   सीबीडी  :  0.3178704486121871\n",
	  "   नान्जिंग  :  0.31493317783451397\n",
	  "   नगरमध्य  :  0.30983542100715433\n",
	  "   स्टेडियमनाम  :  0.2772712419719359\n",
	  "   बीजिंगसंक्षिप्तीकरणसंक्षिप्त  :  0.2654936113048594\n",
	  "   साम्यवादी  :  0.25610701652227763\n",
	  "   कन्फ़्यूशियस  :  0.24573828522281324\n",
	  "   संक्षेपणमानचित्रमानचित्र  :  0.22544891102831235\n",
	  "   जनवादी  :  0.22459203002058745\n",
	  "Words similar to  चीन  :\n",
	  "   जापान  :  0.405028688248321\n",
	  "   देश  :  0.40055558271803016\n",
	  "   भारत  :  0.3995716285696889\n",
	  "   राज्य  :  0.39069412951616933\n",
	  "   क्षेत्र  :  0.3749127085789501\n",
	  "   देशों  :  0.37012434383878395\n",
	  "   रूप  :  0.36528902632282556\n",
	  "   दक्षिण  :  0.3635581165595204\n",
	  "   भारतीय  :  0.3635494350722099\n",
	  "   कारण  :  0.3617244158126584\n",
	  "   प्रदेश  :  0.36105349976074524\n",
	  "Words similar to  लगभग  :\n",
	  "   भारत  :  0.7033013801531995\n",
	  "   राज्य  :  0.7010340265871773\n",
	  "   क्षेत्र  :  0.6996586916081413\n",
	  "   सबसे  :  0.6955835483249215\n",
	  "   रूप  :  0.6804029760453224\n",
	  "   प्रमुख  :  0.6604270171680777\n",
	  "   शहर  :  0.6582098577278507\n",
	  "   प्रदेश  :  0.6492542433137926\n",
	  "   भारतीय  :  0.6418354577936997\n",
	  "   नाम  :  0.6380301234403702\n",
	  "   कारण  :  0.6349314268034624\n",
	  "Words similar to  राजधानी  :\n",
	  "   राज्य  :  0.6002619027478237\n",
	  "   भारत  :  0.594974121624516\n",
	  "   शहर  :  0.5859642833776892\n",
	  "   क्षेत्र  :  0.5824361994285834\n",
	  "   प्रदेश  :  0.5639761489225603\n",
	  "   स्थित  :  0.5602057271275341\n",
	  "   नगर  :  0.5550123083632071\n",
	  "   दिल्ली  :  0.5433338943055831\n",
	  "   प्रमुख  :  0.5425264288048437\n",
	  "   रूप  :  0.541180095809907\n",
	  "   नाम  :  0.5378197874744796\n",
	  "Words similar to  लाख  :\n",
	  "   लगभग  :  0.5064590580344357\n",
	  "   करोड़  :  0.4791813484970636\n",
	  "   राज्य  :  0.47078640237708413\n",
	  "   क्षेत्र  :  0.46187154590200735\n",
	  "   भारत  :  0.4609715967960049\n",
	  "   सबसे  :  0.4576242669137454\n",
	  "   वर्ष  :  0.4521882504028076\n",
	  "   प्रदेश  :  0.4466654397551759\n",
	  "   रूप  :  0.44054452767182617\n",
	  "   जनसंख्या  :  0.4390518203757167\n",
	  "   शहर  :  0.43391355691956207\n",
	  "Words similar to  रैंक  :\n",
	  "   वर्षजीएनपीजीएनपी  :  0.6184530121800731\n",
	  "   राजीएनपी  :  0.6150126342108773\n",
	  "   जीएनपीव्यक्ति  :  0.606950344375186\n",
	  "   जीएनपी  :  0.5933841828784472\n",
	  "   वांव्यक्ति  :  0.5659868610348259\n",
	  "   घनत्वजनसंख्या  :  0.5633417147684632\n",
	  "   वांजनसंख्या  :  0.5588696537580596\n",
	  "   राजालस्थल  :  0.5430510780534924\n",
	  "   वर्षजनसंख्याजनसंख्या  :  0.49413512188668013\n",
	  "   चीनीअंग्रेज़ीबेजिंग  :  0.37671495670077254\n",
	  "   पेइचीङ  :  0.3000641572475026\n",
	  "Words similar to  रूप  :\n",
	  "   भारत  :  0.8651026876907658\n",
	  "   नहीं  :  0.8559426212571674\n",
	  "   नाम  :  0.8188472789393703\n",
	  "   भारतीय  :  0.8158443813901223\n",
	  "   कारण  :  0.8069209779112058\n",
	  "   क्षेत्र  :  0.8055848109420107\n",
	  "   सबसे  :  0.8033449527689137\n",
	  "   राज्य  :  0.7989360560854444\n",
	  "   प्रमुख  :  0.787593666818337\n",
	  "   अनुसार  :  0.7753571541942609\n",
	  "   भाषा  :  0.7529352289757336\n",
	  "Words similar to  सबसे  :\n",
	  "   भारत  :  0.8113265929187796\n",
	  "   रूप  :  0.8033449527689137\n",
	  "   राज्य  :  0.7723690594370708\n",
	  "   क्षेत्र  :  0.7710304116439072\n",
	  "   प्रमुख  :  0.7618093766622419\n",
	  "   नाम  :  0.751138609698589\n",
	  "   भारतीय  :  0.7428421949528092\n",
	  "   नहीं  :  0.7392165248905097\n",
	  "   कारण  :  0.7364599380727292\n",
	  "   शहर  :  0.7285427045584993\n",
	  "   प्रदेश  :  0.7098910626468626\n",
	  "Words similar to  नगर  :\n",
	  "   राज्य  :  0.6833858581255255\n",
	  "   भारत  :  0.683292837467792\n",
	  "   शहर  :  0.6782126336389027\n",
	  "   क्षेत्र  :  0.6624833445597131\n",
	  "   सबसे  :  0.6502517265038262\n",
	  "   प्रदेश  :  0.6394527230915401\n",
	  "   स्थित  :  0.6345834635142947\n",
	  "   रूप  :  0.6273398450590243\n",
	  "   प्रमुख  :  0.6214935763623433\n",
	  "   भारतीय  :  0.618276891562065\n",
	  "   नाम  :  0.6128786442858568\n",
	  "Words similar to  विश्व  :\n",
	  "   भारत  :  0.6613468420946326\n",
	  "   सबसे  :  0.6531975200229437\n",
	  "   रूप  :  0.6518288330938918\n",
	  "   भारतीय  :  0.6245720711335792\n",
	  "   राज्य  :  0.6204098667501816\n",
	  "   कारण  :  0.6143185410475313\n",
	  "   क्षेत्र  :  0.607645873799196\n",
	  "   प्रमुख  :  0.6036642172178875\n",
	  "   नहीं  :  0.6026230012914358\n",
	  "   नाम  :  0.6013333294546997\n",
	  "   वर्ष  :  0.5798727624178608\n"
	 ]
	},
	{
	 "name": "stderr",
	 "output_type": "stream",
	 "text": [
	  "\n"
	 ]
	}
   ],
   "source": [
	"import math\n",
	"\n",
	"def insert_into_sorted_list(sorted_list, element):\n",
	"\tindex = 0\n",
	"\twhile index < len(sorted_list) and sorted_list[index][1] > element[1]:\n",
	"\t\tindex += 1\n",
	"\tsorted_list.insert(index, element)\n",
	"\treturn sorted_list[:-1]\n",
	"\n",
	"def cosine(a,b):\n",
	"\t\tmag_a = math.sqrt(sum(component ** 2 for component in a))\n",
	"\t\tmag_b = math.sqrt(sum(component ** 2 for component in b))\n",
	"\t\tdot_product = sum(ai * bi for ai, bi in zip(a, b))\n",
	"\t\tif mag_a == 0 or mag_b ==0:\n",
	"\t\t\treturn 0\n",
	"\t\telse:\n",
	"\t\t\treturn dot_product/(mag_a*mag_b)\n",
	"\n",
	"def find_nearest_neighbor_of_noun(index):\n",
	"\tl=[(distinct_vocab[0],(cosine(ppmi_matrix[index], ppmi_matrix[0]))) for _ in range(11)]\n",
	"\tfor i in tqdm(range(1,len(distinct_vocab))):\n",
	"\t\tif i!=index and cosine(ppmi_matrix[index],ppmi_matrix[i])>l[10][1]:\n",
	"\t\t\t\tl=insert_into_sorted_list(l,(distinct_vocab[i],(cosine(ppmi_matrix[index], ppmi_matrix[i]))))\n",
	"\treturn l\n",
	"\n",
	"nearest_neighbour_dict={}\n",
	"\n",
	"@calculate_time\n",
	"@memory_location\n",
	"def nearest_seq():\n",
	"\tfor x in tqdm(most_common_elements):\n",
	"\t\tnearest_neighbour_dict[x]=find_nearest_neighbor_of_noun(distinct_vocab.index(x))\n",
	"\n",
	"nearest_seq()\n",
	"\n",
	"for i in nearest_neighbour_dict:\n",
	"\tprint(\"Words similar to \",i,\" :\")\n",
	"\tfor j in nearest_neighbour_dict[i]:\n",
	"\t\tprint (\"  \",j[0],\" : \",j[1])\n"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": 17,
   "metadata": {},
   "outputs": [
	{
	 "name": "stdout",
	 "output_type": "stream",
	 "text": [
	  "<zip object at 0x747d22ca4d40>\n"
	 ]
	}
   ],
   "source": [
	"grams = (ngrams(tokens, 6))  # Generate ngrams of size 6\n",
	"print(grams)"
   ]
  },
  {
   "cell_type": "markdown",
   "metadata": {},
   "source": [
	"Parallelized co-occurrence matrix generation using multiprocessing.\n"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": 18,
   "metadata": {},
   "outputs": [
	{
	 "name": "stdout",
	 "output_type": "stream",
	 "text": [
	  "Memory location of create_matrix_p: 128081225495584\n"
	 ]
	},
	{
	 "name": "stderr",
	 "output_type": "stream",
	 "text": [
	  "100%|██████████| 16/16 [00:00<00:00, 225.00it/s]\n"
	 ]
	},
	{
	 "name": "stdout",
	 "output_type": "stream",
	 "text": [
	  "Execution time of wrapper: 0.2642648220062256 seconds\n"
	 ]
	}
   ],
   "source": [
	"n = vocabulary_size  # Size of the vocabulary\n",
	"p_mat = multiprocessing.Array('d', n*n)  # Shared memory array to store partial co-occurrence matrix\n",
	"\n",
	"def grams_partition(grams, n):\n",
	"    # Calculate the size of each partition\n",
	"    partition_size = len(grams) // n\n",
	"    # Use list comprehension to create partitions\n",
	"    partitions = [grams[i * partition_size: (i + 1) * partition_size] for i in range(n)]\n",
	"    # If there's any remaining elements, distribute them to the partitions\n",
	"    for i in range(len(grams) % n):\n",
	"        partitions[i % n].append(grams[(n * partition_size) + i])\n",
	"    return partitions\n",
	"\n",
	"def compile_grams(gram, p_mat):\n",
	"\t\"\"\"\n",
	"\tCompile co-occurrence counts for a gram and update the shared memory array.\n",
	"\n",
	"\tArgs:\n",
	"\t\tgram (tuple): The gram to compile co-occurrence counts for.\n",
	"\t\tp_mat (multiprocessing.Array): Shared memory array to store partial co-occurrence matrix.\n",
	"\n",
	"\tReturns:\n",
	"\t\tNone\n",
	"\t\"\"\"\n",
	"\tramp = [0, 1, 2, 3, 4, 5]  # Ramp sequence for weight calculation\n",
	"\tleng = len(distinct_vocab)  # Length of the distinct vocabulary\n",
	"\n",
	"\tx = list(gram)\n",
	"\n",
	"\t# Forward pass\n",
	"\tif x[0] in distinct_vocab:\n",
	"\t\tfor i in range(1, len(x)):\n",
	"\t\t\tif x[i] in distinct_vocab:\n",
	"\t\t\t\t# Update co-occurrence count based on weight\n",
	"\t\t\t\tp_mat[distinct_vocab.index(x[0]) * leng + distinct_vocab.index(x[i])] += 5 - ramp[i]\n",
	"\n",
	"\tx.reverse()\n",
	"\n",
	"\t# Backward pass\n",
	"\tif x[0] in distinct_vocab:\n",
	"\t\tfor i in range(1, len(x)):\n",
	"\t\t\tif x[i] in distinct_vocab:\n",
	"\t\t\t\t# Update co-occurrence count based on weight\n",
	"\t\t\t\tp_mat[distinct_vocab.index(x[0]) * leng + distinct_vocab.index(x[i])] += 5 - ramp[i]\n",
	"\n",
	"@calculate_time\n",
	"@memory_location\n",
	"def create_matrix_p(p_mat):\n",
	"\t\"\"\"\n",
	"\tCreate a co-occurrence matrix in parallel using multiprocessing.\n",
	"\n",
	"\tArgs:\n",
	"\t\tp_mat (multiprocessing.Array): Shared memory array to store partial co-occurrence matrix.\n",
	"\n",
	"\tReturns:\n",
	"\t\tNone\n",
	"\t\"\"\"\t\n",
	"\tcount=multiprocessing.cpu_count()\n",
	"\tng = (ngrams(tokens, 6))  # Generate ngrams of size 6\n",
	"\tgrams=[]\n",
	"\tfor g in ng:\n",
	"\t\tgrams.append(list(g))\n",
	"\tjobs = []\n",
	"\tpartitioned=grams_partition(grams,count)\n",
	"\n",
	"\t# Create a process for each gram to compile co-occurrence counts\n",
	"\tfor gram in tqdm(partitioned):\n",
	"\t\tp = multiprocessing.Process(target=compile_grams, args=(gram, p_mat,))\n",
	"\t\tp.start()\n",
	"\t\tjobs.append(p)\n",
	"\n",
	"\t# Wait for all processes to finish\n",
	"\tfor job in jobs:\n",
	"\t\tjob.join()\n",
	"\n",
	"# Create the co-occurrence matrix in parallel\n",
	"create_matrix_p(p_mat)\n",
	"\n",
	"# Convert the shared memory array to a numpy array\n",
	"p_matrix = np.zeros((n, n), dtype=np.int64)\n",
	"for i in range(n):\n",
	"\tfor j in range(n):\n",
	"\t\tp_matrix[i][j] = p_mat[i * n + j]\n"
   ]
  },
  {
   "cell_type": "markdown",
   "metadata": {},
   "source": [
	"\n",
	"Parallel Nearest Neighbor Search"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": 19,
   "metadata": {},
   "outputs": [
	{
	 "name": "stdout",
	 "output_type": "stream",
	 "text": [
	  "Memory location of parallel_nearest: 128080554787040\n"
	 ]
	},
	{
	 "name": "stderr",
	 "output_type": "stream",
	 "text": [
	  "100%|██████████| 2276/2276 [00:02<00:00, 1110.51it/s]\n",
	  "100%|██████████| 2276/2276 [00:02<00:00, 1055.49it/s]\n",
	  "100%|██████████| 2276/2276 [00:02<00:00, 1000.05it/s]\n",
	  "100%|██████████| 2276/2276 [00:02<00:00, 907.14it/s]]\n",
	  "100%|██████████| 2276/2276 [00:02<00:00, 914.87it/s] \n",
	  "100%|██████████| 2276/2276 [00:02<00:00, 870.73it/s]]\n",
	  "100%|██████████| 2276/2276 [00:02<00:00, 873.16it/s] \n",
	  "100%|██████████| 2276/2276 [00:02<00:00, 874.27it/s] \n",
	  "100%|██████████| 2276/2276 [00:02<00:00, 859.23it/s] \n",
	  "100%|██████████| 2276/2276 [00:02<00:00, 845.38it/s]\n"
	 ]
	},
	{
	 "name": "stdout",
	 "output_type": "stream",
	 "text": [
	  "Execution time of wrapper: 2.7557485103607178 seconds\n",
	  "{'नगर': [('राज्य', 0.6833858581255255), ('भारत', 0.683292837467792), ('शहर', 0.6782126336389027), ('क्षेत्र', 0.6624833445597131), ('सबसे', 0.6502517265038262), ('प्रदेश', 0.6394527230915401), ('स्थित', 0.6345834635142947), ('रूप', 0.6273398450590243), ('प्रमुख', 0.6214935763623433), ('भारतीय', 0.618276891562065), ('नाम', 0.6128786442858568)], 'राजधानी': [('राज्य', 0.6002619027478237), ('भारत', 0.594974121624516), ('शहर', 0.5859642833776892), ('क्षेत्र', 0.5824361994285834), ('प्रदेश', 0.5639761489225603), ('स्थित', 0.5602057271275341), ('नगर', 0.5550123083632071), ('दिल्ली', 0.5433338943055831), ('प्रमुख', 0.5425264288048437), ('रूप', 0.541180095809907), ('नाम', 0.5378197874744796)], 'रूप': [('भारत', 0.8651026876907658), ('नहीं', 0.8559426212571674), ('नाम', 0.8188472789393703), ('भारतीय', 0.8158443813901223), ('कारण', 0.8069209779112058), ('क्षेत्र', 0.8055848109420107), ('सबसे', 0.8033449527689137), ('राज्य', 0.7989360560854444), ('प्रमुख', 0.787593666818337), ('अनुसार', 0.7753571541942609), ('भाषा', 0.7529352289757336)], 'चीन': [('जापान', 0.405028688248321), ('देश', 0.40055558271803016), ('भारत', 0.3995716285696889), ('राज्य', 0.39069412951616933), ('क्षेत्र', 0.3749127085789501), ('देशों', 0.37012434383878395), ('रूप', 0.36528902632282556), ('दक्षिण', 0.3635581165595204), ('भारतीय', 0.3635494350722099), ('कारण', 0.3617244158126584), ('प्रदेश', 0.36105349976074524)], 'सबसे': [('भारत', 0.8113265929187796), ('रूप', 0.8033449527689137), ('राज्य', 0.7723690594370708), ('क्षेत्र', 0.7710304116439072), ('प्रमुख', 0.7618093766622419), ('नाम', 0.751138609698589), ('भारतीय', 0.7428421949528092), ('नहीं', 0.7392165248905097), ('कारण', 0.7364599380727292), ('शहर', 0.7285427045584993), ('प्रदेश', 0.7098910626468626)], 'बीजिंग': [('एन्साइक्लोपीडिया', 0.37147304915123436), ('ब्रिटेनिका', 0.36400208114269533), ('सीबीडी', 0.3178704486121871), ('नान्जिंग', 0.31493317783451397), ('नगरमध्य', 0.30983542100715433), ('स्टेडियमनाम', 0.2772712419719359), ('बीजिंगसंक्षिप्तीकरणसंक्षिप्त', 0.2654936113048594), ('साम्यवादी', 0.25610701652227763), ('कन्फ़्यूशियस', 0.24573828522281324), ('संक्षेपणमानचित्रमानचित्र', 0.22544891102831235), ('जनवादी', 0.22459203002058745)], 'लगभग': [('भारत', 0.7033013801531995), ('राज्य', 0.7010340265871773), ('क्षेत्र', 0.6996586916081413), ('सबसे', 0.6955835483249215), ('रूप', 0.6804029760453224), ('प्रमुख', 0.6604270171680777), ('शहर', 0.6582098577278507), ('प्रदेश', 0.6492542433137926), ('भारतीय', 0.6418354577936997), ('नाम', 0.6380301234403702), ('कारण', 0.6349314268034624)], 'रैंक': [('वर्षजीएनपीजीएनपी', 0.6184530121800731), ('राजीएनपी', 0.6150126342108773), ('जीएनपीव्यक्ति', 0.606950344375186), ('जीएनपी', 0.5933841828784472), ('वांव्यक्ति', 0.5659868610348259), ('घनत्वजनसंख्या', 0.5633417147684632), ('वांजनसंख्या', 0.5588696537580596), ('राजालस्थल', 0.5430510780534924), ('वर्षजनसंख्याजनसंख्या', 0.49413512188668013), ('चीनीअंग्रेज़ीबेजिंग', 0.37671495670077254), ('पेइचीङ', 0.3000641572475026)], 'विश्व': [('भारत', 0.6613468420946326), ('सबसे', 0.6531975200229437), ('रूप', 0.6518288330938918), ('भारतीय', 0.6245720711335792), ('राज्य', 0.6204098667501816), ('कारण', 0.6143185410475313), ('क्षेत्र', 0.607645873799196), ('प्रमुख', 0.6036642172178875), ('नहीं', 0.6026230012914358), ('नाम', 0.6013333294546997), ('वर्ष', 0.5798727624178608)], 'लाख': [('लगभग', 0.5064590580344357), ('करोड़', 0.4791813484970636), ('राज्य', 0.47078640237708413), ('क्षेत्र', 0.46187154590200735), ('भारत', 0.4609715967960049), ('सबसे', 0.4576242669137454), ('वर्ष', 0.4521882504028076), ('प्रदेश', 0.4466654397551759), ('रूप', 0.44054452767182617), ('जनसंख्या', 0.4390518203757167), ('शहर', 0.43391355691956207)]}\n"
	 ]
	}
   ],
   "source": [
	"import math\n",
	"import multiprocessing\n",
	"\n",
	"# Using a multiprocessing Manager to create a shared dictionary\n",
	"neigh = multiprocessing.Manager().dict()\n",
	"\n",
	"def p_insert_into_sorted_list(sorted_list, element):\n",
	"\t\"\"\"\n",
	"\tInsert an element into a sorted list in descending order based on the second value of each tuple.\n",
	"\n",
	"\tArgs:\n",
	"\t\tsorted_list (list): The sorted list to insert the element into.\n",
	"\t\telement (tuple): The element to insert into the sorted list.\n",
	"\n",
	"\tReturns:\n",
	"\t\tlist: The sorted list with the new element inserted.\n",
	"\t\"\"\"\n",
	"\tindex = 0\n",
	"\twhile index < len(sorted_list) and sorted_list[index][1] > element[1]:\n",
	"\t\tindex += 1\n",
	"\tsorted_list.insert(index, element)\n",
	"\treturn sorted_list[:-1]\n",
	"\n",
	"def p_cosine(a, b):\n",
	"\t\"\"\"\n",
	"\tCalculate the cosine similarity between two vectors.\n",
	"\n",
	"\tArgs:\n",
	"\t\ta (list): First vector.\n",
	"\t\tb (list): Second vector.\n",
	"\n",
	"\tReturns:\n",
	"\t\tfloat: Cosine similarity between the two vectors.\n",
	"\t\"\"\"\n",
	"\tmag_a = math.sqrt(sum(component ** 2 for component in a))\n",
	"\tmag_b = math.sqrt(sum(component ** 2 for component in b))\n",
	"\tdot_product = sum(ai * bi for ai, bi in zip(a, b))\n",
	"\tif mag_a == 0 or mag_b == 0:\n",
	"\t\treturn 0\n",
	"\telse:\n",
	"\t\treturn dot_product / (mag_a * mag_b)\n",
	"\n",
	"def p_find_nearest_neighbor_of_noun(index, neigh):\n",
	"\t\"\"\"\n",
	"\tFind the nearest neighbors of a noun in the ppmi_matrix in parallel.\n",
	"\n",
	"\tArgs:\n",
	"\t\tindex (int): Index of the noun in the distinct_vocab.\n",
	"\t\tneigh (multiprocessing.Manager().dict): Shared dictionary to store nearest neighbors.\n",
	"\n",
	"\tReturns:\n",
	"\t\tNone\n",
	"\t\"\"\"\n",
	"\t# Initialize a list with the first noun and its cosine similarity score\n",
	"\tl = [(distinct_vocab[0], (p_cosine(ppmi_matrix[index], ppmi_matrix[0]))) for _ in range(11)]\n",
	"\t\n",
	"\t# Iterate over other nouns\n",
	"\tfor i in tqdm(range(1, n)):\n",
	"\t\tif i != index:\n",
	"\t\t\tif p_cosine(ppmi_matrix[index], ppmi_matrix[i]) > l[10][1]:\n",
	"\t\t\t\t# Update the list with the nearest neighbors\n",
	"\t\t\t\tl = p_insert_into_sorted_list(l, (distinct_vocab[i], (p_cosine(ppmi_matrix[index], ppmi_matrix[i]))))\n",
	"\n",
	"\t# Store the list of nearest neighbors in the shared dictionary\n",
	"\tneigh[distinct_vocab[index]] = l\n",
	"\n",
	"@calculate_time\n",
	"@memory_location\n",
	"def parallel_nearest():\n",
	"\t\"\"\"\n",
	"\tFind nearest neighbors for each noun in most_common_elements in parallel.\n",
	"\n",
	"\tReturns:\n",
	"\t\tNone\n",
	"\t\"\"\"\n",
	"\tjobs = []\n",
	"\tfor x in (most_common_elements):\n",
	"\t\t# Create a process for each noun to find nearest neighbors\n",
	"\t\tp = multiprocessing.Process(target=p_find_nearest_neighbor_of_noun, args=(distinct_vocab.index(x), neigh,))\n",
	"\t\tp.start()\n",
	"\t\tjobs.append(p)\n",
	"\n",
	"\t# Wait for all processes to finish\n",
	"\tfor job in jobs:\n",
	"\t\tjob.join()\n",
	"\n",
	"# Execute parallel_nearest function to find nearest neighbors for each noun in parallel\n",
	"parallel_nearest()\n",
	"\n",
	"# Print the shared dictionary containing nearest neighbors for each noun\n",
	"print(neigh)\n"
   ]
  }
 ],
 "metadata": {
  "kernelspec": {
   "display_name": "Python 3",
   "language": "python",
   "name": "python3"
  },
  "language_info": {
   "codemirror_mode": {
	"name": "ipython",
	"version": 3
   },
   "file_extension": ".py",
   "mimetype": "text/x-python",
   "name": "python",
   "nbconvert_exporter": "python",
   "pygments_lexer": "ipython3",
   "version": "3.12.3"
  }
 },
 "nbformat": 4,
 "nbformat_minor": 2
}
