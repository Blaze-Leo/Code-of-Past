
# %% [markdown]
# Generating the Top 50 words and choosing the Top 10 nouns from them

# %%
top_15 = vocab_counts.most_common(15)
top_15 = [i for i, _ in top_15]
print("Top 15 popular words : ", top_15)

nouns = [top_15[0], top_15[1], top_15[2], top_15[4], top_15[5],
		top_15[6], top_15[7], top_15[8], top_15[9], top_15[11]]
print("Top 10 popular nouns : ", nouns)

# %% [markdown]
# Displaying similar words for the nouns

# %%
for noun in nouns:
	target_word = noun
	similar_words = find_similar_words(target_word, vocab, ppmi_matrix)

	print(f"Words similar to '{target_word}':")
	for word, similarity in similar_words:
		print(f"	{word}: {similarity}")


