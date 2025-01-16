'''
Filename: stock_analyzer/stock.py
Created Date: Wednesday , March 13th 2024, 5:25:58 am
Author: Anurag Gupta and BMC202309
'''


class stock():

    def __init__(self, values):
        self.values=values

    def date(self):
        return self.values[0]

    def open(self):
        return self.values[1]

    def high(self):
        return self.values[2]

    def low(self):
        return self.values[3]

    def close(self):
        return self.values[4]

    def adj_close(self):
        return self.values[5]

    def volume(self):
        return self.values[6]
