# https://colab.research.google.com/drive/1MF_tbivSvSfXvLRLJifDzkQ_8FjWukYy?usp=sharing#scrollTo=fLnJLSLQnTxV

from alpaca_trade_api.stream import Stream
from alpaca_trade_api.common import URL

async def trade_callback(t):
    print('trade', t)


async def quote_callback(q):
    print('quote', q)

async def news_callback(q):
    # TODO: Add a parser that parses any html tags that it may contain as it's a stream, might not have been yet removed.
    print('news', q)

# Initiate Class Instance
stream = Stream("PKZLW20DRB2DJFPPRMZV",
                "HS655suik2zGGRFXbqe5b7Xc1GRT8jHFVxQxzD1g",
                base_url=URL('https://paper-api.alpaca.markets'),
                data_feed='iex')  # <- replace to 'sip' if you have PRO subscription

# subscribing to event
#stream.subscribe_trades(trade_callback, 'AAPL')
#stream.subscribe_quotes(quote_callback, 'TSLA')
stream.subscribe_news(news_callback, '*')
stream.run()