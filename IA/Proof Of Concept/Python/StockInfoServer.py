from flask import Flask, request, send_from_directory
app = Flask(__name__)


@app.route('/fundamentals', methods=['GET'])
def fundamentals():
    ticker = request.args.get('ticker')
    
    widget = """
<div class="tradingview-widget-container">
  <div class="tradingview-widget-container__widget"></div>
  <div class="tradingview-widget-copyright"><a href="https://www.tradingview.com/symbols/""" + ticker + """/financials-overview/" rel="noopener" target="_blank"><span class="blue-text">""" + ticker + """ Fundamental Data</span></a> by TradingView</div>
  <script type="text/javascript" src="https://s3.tradingview.com/external-embedding/embed-widget-financials.js" async>
  {
  "symbol": """ + ticker + """",
  "colorTheme": "light",
  "isTransparent": false,
  "largeChartUrl": "",
  "displayMode": "regular",
  "width": "600",
  "height": "830",
  "locale": "en"
}
  </script>
</div>
"""

    return widget





# start the flask server
if __name__ == '__main__':
    app.run(debug=True)