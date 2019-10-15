import numpy as np
from sklearn.linear_model import LinearRegression
import matplotlib.pyplot as plt

x = np.array([2, 54, 23, 68, 10, 40, 15, 33, 39]).reshape(-1, 1)
y = np.array([4, 8, 12, 2, 7, 10, 40, 11, 22])

model = LinearRegression()

model = model.fit(x, y)

r_sq = model.score(x, y)

intercept = model.intercept_
coefficient = model.coef_[0]
print(str(intercept) + " " + str(coefficient))

# stacked = np.vstack((x, y)).T

y_pred = model.predict(x)

print(str(len(x)) + "   " + str(len(y)))
plt.scatter(x, y)
plt.plot(x, y_pred)


plt.show()
# fig = plt.figure()
#
# axes1 = fig.add_axes(x)
# axes2 = fig.add_axes(y)
#
# fig.show()
