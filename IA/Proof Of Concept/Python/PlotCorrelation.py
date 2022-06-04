import pandas_datareader as web
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns

# to get inputs from java
import sys

# function to get data from yahoo finance
def get_data(ticker, start_date, end_date):
    return web.DataReader(ticker, 'yahoo', start_date, end_date)

# function to get data from a csv file // TODO: See if the data is the same as the data from yahoo finance
def get_data_from_csv(file_name):
    df = pd.read_csv(file_name)
    return df


# function to plot the coorelation between a list of stocks by calling get_data to get the data
def plot_correlation(tickers, start_date, end_date):
    df = get_data(tickers, start_date, end_date)["Adj Close"]
    
    df = df.dropna()
    df = df.pct_change()
    df = df.dropna()
    sns.heatmap(df.corr(), annot=True)
    # change the sns color palette to be green and red
    sns.set_palette("RdBu_r")
    plt.show()


plot_correlation(sys.argv[1].split(","), sys.argv[2], sys.argv[3])
