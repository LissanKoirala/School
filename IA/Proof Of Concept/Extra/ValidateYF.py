# script to validate the list of stocks in the original_stocks_list.csv file against the Yahoo Finance API
# and output the results to a new file

import requests, time
import alpaca_trade_api as tradeapi
from multiprocessing import Process


headers = {'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0'}

# TODO: COMPARE WITH ALPACA API TOO... GETTING LOTS OF CRAP STOCKS 
api_key = 'PKZLW20DRB2DJFPPRMZV'
api_secret = 'HS655suik2zGGRFXbqe5b7Xc1GRT8jHFVxQxzD1g'
base_url = 'https://paper-api.alpaca.markets'

# instantiate REST API
api = tradeapi.REST(api_key, api_secret, base_url, api_version='v2')

def check_alpaca(ticker):

    # Check if ticker is tradable on the Alpaca platform.
    try:
        asset = api.get_asset(ticker)
        if asset.tradable:
            #print("Not tradable", end=", ")
            return True
    except:
        return False



def validate_stocks(stocks):
    new_list = ""
    for i in stocks:
        ticker = i.split(',')[0]
        YFticker = ticker.replace(".","-")
        name = i.split(',')[1].replace("\n", "")

        print("STOCK: " + name + f" ({ticker})", end="")

        # checking if stock exists in alpaca first, then YF, saves a ton of bandwith
        if check_alpaca(ticker):
            print(", Found in Alpaca", end="")

            # getting one day of stock's data to limit the bandwidth usage as much as possible
            link = f'https://query1.finance.yahoo.com/v7/finance/download/{YFticker}?period1=1654214400&period2=1654387200&interval=1d&events=history&includeAdjustedClose=true'
            r = requests.get(link, headers=headers)
            data = r.text
            
            if data != "404 Not Found: No data found, symbol may be delisted": # if the stock is not found, don't add it to the new list
                new_list += ticker + "," + name + "\n"
                print(", Found in Yahoo Finance", end="")

        print("")


    # write new_list to a new file
    f = open('new_stocks_list.csv', 'w', encoding='utf-8')
    f.write(new_list)
    f.close()


def validate_cryptos(cryptos):
    new_list = ""
    for i in cryptos:
        # adding USD as the pair
        ticker = i.split(',')[0] + "USD"
        YFticker = i.split(',')[0] + "-USD"
        name = i.split(',')[1].replace("\n", "")

        print("CRYPTO: " + name + f" ({ticker})", end="")

        # checking if stock exists in alpaca first, then YF, saves a ton of bandwith
        if check_alpaca(ticker):
            print(", Found in Alpaca", end="")

            # getting one day of stock's data to limit the bandwidth usage as much as possible
            link = f'https://query1.finance.yahoo.com/v7/finance/download/{YFticker}?period1=1654214400&period2=1654387200&interval=1d&events=history&includeAdjustedClose=true'
            r = requests.get(link, headers=headers)
            data = r.text
            
            if data != "404 Not Found: No data found, symbol may be delisted": # if the stock is not found, don't add it to the new list
                new_list += ticker + "," + name + "\n"
                print(", Found in Yahoo Finance", end="")
        
        print("")

        
    # write new_list to a new file
    f = open('new_crypto_list.csv', 'w', encoding='utf-8')
    f.write(new_list)
    f.close()



if __name__ == "__main__":

    f = open('original_stocks_list.csv', 'r', encoding='utf-8')
    stocks = f.readlines()
    f.close()

    f = open('original_cryptos_list.csv', 'r', encoding='utf-8')
    cryptos = f.readlines()
    f.close()


    st = time.time()

    stock_p = Process(target=validate_stocks, args=(stocks,))
    stock_p.start()

    crypto_p = Process(target=validate_cryptos, args=(cryptos,))
    crypto_p.start()

    stock_p.join()
    crypto_p.join()

    et = time.time()

    # get the execution time
    elapsed_time = et - st
    print('Execution time:', elapsed_time, 'seconds')