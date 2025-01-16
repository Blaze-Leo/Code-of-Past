'''
Filename: stock_analyzer/main.py
Created Date: Wednesday , March 13th 2024, 5:25:58 am
Author: Anurag Gupta and BMC202309
'''


from stock_analyzer.quant import quant



if __name__ == '__main__':
    q = quant(
        "/content/drive/MyDrive/Colab Notebooks/AnuragBMC202309_Assignment03/INFY.NS.csv")
    #check for date order
    q.sort_by('date','des')
    q.pprint_sorted_data()
    print("\n\n")
    #check for volume order
    q.sort_by('volume','des')
    q.pprint_sorted_data()
    print("\n\n")
    #print the yearly  extremes
    q.pprint_yearly_extremes()