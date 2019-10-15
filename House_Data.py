import pandas as pd
from sklearn.linear_model import LinearRegression
import numpy as np
import matplotlib.pyplot as plt

pd.set_option('display.max_rows', 500)
pd.set_option('display.max_columns', 500)
pd.set_option('display.width', 1000)

hd = pd.DataFrame(data=pd.read_csv("house_data.csv"))
bedroom_column = np.array([])
bathroom_column = np.array([])
sqrft_column = np.array([])
price_column = np.array([])

for i in range(len(hd)):
    price_column = np.append(price_column, hd.iloc[i, 2])
    bedroom_column = np.append(bedroom_column, hd.iloc[i, 3])
    bathroom_column = np.append(bathroom_column, hd.iloc[i, 4])
    sqrft_column = np.append(sqrft_column, hd.iloc[i, 5])

stacked = np.vstack((bedroom_column, bathroom_column, sqrft_column)).T

model = LinearRegression()

model = model.fit(stacked, price_column)

# print(model.score(stacked, price_column))

price_column_pred = model.predict(stacked)

# example_predict = model.predict((3, 1, 1180))
# print(example_predict)


def pred_formula(x1, x2, x3):
    result = model.intercept_ + model.coef_[0]*x1 + model.coef_[1]*x2 + model.coef_[2]*x3
    return result


print(pred_formula(3, 1, 1180))
# plt.scatter(stacked[:, 0], stacked[:, 1], stacked[:, 2], price_column)
plt.plot(stacked[:, 0], stacked[:, 1], stacked[:, 2], price_column_pred, "o")
plt.show()
# print(str(len(stacked)) + "   " + str(len(price_column)))
