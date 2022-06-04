## Code if wanting to plot the data to a browser that is interactive instead with just the image

import matplotlib as mpl
mpl.use('WebAgg')
import matplotlib.pyplot as plt
import numpy as np


a=np.random.random(100)
b=np.random.random(100)
plt.plot(a,b)
plt.show()