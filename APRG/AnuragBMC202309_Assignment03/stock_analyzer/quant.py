'''
Filename: stock_analyzer/quant.py
Created Date: Wednesday , March 13th 2024, 5:25:58 am
Author: Anurag Gupta and BMC202309
'''

from stock import stock

class quant():
    def __init__(self, filename):
        #define the original list to store stock objects
        self.filename = filename
        self.stock_data = []
        self.__read_file()

    def __read_file(self):
        with open(self.filename, 'r', encoding='utf -8') as self.file:
            self.file_list = self.file.readlines()

        self.file_list[-1] += '\n'
        #add n to the last line and then remove \n from all the lines
        for i in range(len(self.file_list)):
            self.file_list[i] = self.file_list[i][:-1]

        for line in  self.file_list:
            self.__process_lines(line)

    def __process_lines(self, line):
        self.words = line.split(',')
        self.stock_obj = stock(self.words)
        #pass the list as a stock object
        self.stock_data.append(self.stock_obj)

    def __merge_sort(self, arr):
        if len(arr) > 1:

            # Create sub_array2 ← A[start..mid] and sub_array2 ← A[mid+1..end]
            mid = len(arr)//2
            sub_array1 = arr[:mid]
            sub_array2 = arr[mid:]

            # Sort the two halves
            sub_array1 = self.__merge_sort(sub_array1)
            sub_array2 = self.__merge_sort(sub_array2)
            # Initial values for pointers that we use to keep track of where we are in each array
            i = j = k = 0

        # Until we reach the end of either start or end, pick larger among
        # elements start and end and place them in the correct position in the sorted array
            while i < len(sub_array1) and j < len(sub_array2):
                ele1 = sub_array1[i][0].replace('-', '')
                ele2 = sub_array2[j][0].replace('-', '')

                if eval(ele1) < eval(ele2):
                    arr[k] = sub_array1[i]
                    i += 1
                else:
                    arr[k] = sub_array2[j]
                    j += 1

                k += 1

        # When all elements are traversed in either arr1 or arr2,
        # pick up the remaining elements and put in sorted array
            while i < len(sub_array1):
                arr[k] = sub_array1[i]
                i += 1
                k += 1

            while j < len(sub_array2):
                arr[k] = sub_array2[j]
                j += 1
                k += 1

        return arr

    def sort_by(self, key='date', sort_order='asc'):
        self.order=sort_order
        self.key_pos = {'date': 0, 'open': 1, 'high': 2,
                        'low': 3, 'close': 4, 'adj_close': 5, 'volume': 6}
        self.pos = self.key_pos.get(key)
        self.ordered = []
        self.words = []
        for st in self.stock_data:
            self.words=[]
            self.words.append(st.date())
            self.words.append(st.open())
            self.words.append(st.high())
            self.words.append(st.low())
            self.words.append(st.close())
            self.words.append(st.adj_close())
            self.words.append(st.volume())
            #order the words in the way they will be sorted
            self.s = [self.words[(0+self.pos+7) % 7], self.words[(1+self.pos+7) % 7], self.words[(2+self.pos+7) % 7], self.words[(
                3+self.pos+7) % 7], self.words[(4+self.pos+7) % 7], self.words[(5+self.pos+7) % 7], self.words[(6+self.pos+7) % 7]]
            #null objects dont need to be sorted
            if self.s[0]!='null':
                self.ordered.append(self.s)

        self.sorted = self.__merge_sort(self.ordered[1:])
        #for descending just reverse the ordering
        if sort_order == 'des':
            self.sorted.reverse()

        self.sorted.insert(0, self.ordered[0])
        self.stock_sorted = []

        #transfer the list of values into stock objects
        for i in self.sorted:
            self.stock_sorted.append(stock(i))

    def yearly_extremes(self) -> dict:
        self.sort_by("date","des")
        self.extremes = {}

        for st in self.stock_sorted[1:]:
            if st.adj_close()=="null":
                continue
            d = st.date()[:4]
            #if a dict on the current year hasn't been created then initialize that year
            if self.extremes.get(d) == None:
                self.extremes[d] = {'high': (eval(st.adj_close()), st.volume(), st.date()), 'low': (
                    eval(st.adj_close()), st.volume(), st.date())}
            else:
                #check for lower or higher aj_close values
                if eval(st.adj_close()) > (self.extremes[d]['high'][0]):
                    self.extremes[d]['high'] = (
                        eval(st.adj_close()), st.volume(), st.date())
                if eval(st.adj_close()) < (self.extremes[d]['low'][0]):
                    self.extremes[d]['low'] = (
                        eval(st.adj_close()), st.volume(), st.date())

        return self.extremes

    def pprint_yearly_extremes(self):
        self.extremes = self.yearly_extremes()
        d = "Year"
        h = "(High, volume,date)"
        l = "(Low, volume, date)"
        print(f"{d:<7}{h:<40}{l:<40}")
        for ex in self.extremes:
            high = self.extremes[ex]['high']
            low = self.extremes[ex]['low']
            #round the values off to two digits precision
            highs = [((int)((high[0]+0.005)*100))/100,high[1],high[2]]
            lows = [((int)((low[0]+0.005)*100))/100,low[1],low[2]]
            h = str(highs)
            l = str(lows)
            #since i have changes the precision using a list, make it look like a tuple
            h="("+h[1:-1]+")"
            l="("+l[1:-1]+")"
            date = high[2][:4]
            print(f"{date:<7}{h:<40}{l:<40}")

    def pprint_sorted_data(self, top=10):
        d = self.stock_sorted[0].date()
        o = self.stock_sorted[0].open()
        h = self.stock_sorted[0].high()
        l = self.stock_sorted[0].low()
        c = self.stock_sorted[0].close()
        a = self.stock_sorted[0].adj_close()
        v = self.stock_sorted[0].volume()
        #print the headers in order
        print(f"{d:<17}{o:<17}{h:<17}{l:<17}{c:<17}{a:<17}{v:<17}")
        for i in range(1, top+1):
            d = self.stock_sorted[i].date()
            o = self.stock_sorted[i].open()
            h = self.stock_sorted[i].high()
            l = self.stock_sorted[i].low()
            c = self.stock_sorted[i].close()
            a = self.stock_sorted[i].adj_close()
            v = self.stock_sorted[i].volume()

            #round off everything other than the date
            d = ((int)((eval(d)+0.005)*100))/100 if '-' not in d and d!='null' else d
            o = ((int)((eval(o)+0.005)*100))/100 if '-' not in o and o!='null' else o
            h = ((int)((eval(h)+0.005)*100))/100 if '-' not in h and h!='null' else h
            l = ((int)((eval(l)+0.005)*100))/100 if '-' not in l and l!='null' else l
            c = ((int)((eval(c)+0.005)*100))/100 if '-' not in c and c!='null' else c
            a = ((int)((eval(a)+0.005)*100))/100 if '-' not in a and a!='null' else a
            v = ((int)((eval(v)+0.005)*100))/100 if '-' not in v and v!='null' else v

            #if volume has been rounded off then find out witch variable is storing volume and remove the .0 at the end
            if self.pos==0:
                v=int(v)
            if self.pos==1:
                a=int(a)
            if self.pos==2:
                c=int(c)
            if self.pos==3:
                l=int(l)
            if self.pos==4:
                h=int(h)
            if self.pos==5:
                o=int(o)
            if self.pos==6:
                d=int(d)

            print(f"{d:<17}{o:<17}{h:<17}{l:<17}{c:<17}{a:<17}{v:<17}")



