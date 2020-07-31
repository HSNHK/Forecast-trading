# Just for fun

😂😂 صرفا برای خنده 

This project is for the second semester student of Android programming course.
The overall plan of the project is that we teach this pattern to the machine and the machine guesses what will happen in the future.
We give a series of inputs and an output. We can give several inputs and one output.
The purpose of this project is to analyze the stock market and we give the stock market conditions such as sanctions and the volume of past transactions to the machine along with the result.

Now if we give the current information to the machine, it will give us an output according to the previous patterns.

## Installation

Use the package manager [pip](https://pip.pypa.io/en/stable/) to install foobar.

```bash
pip install flask
pip install sklearn
```

## Usage

```python
import foobar

from flask import Flask,request,jsonify
from sklearn import tree
from datetime import datetime

...

clf=tree.DecisionTreeClassifier()
clf.fit(x,y)

clf.predict(~)

```
## Sample Pattern

1:0:1:0:0<br>
1:1:1:0:0<br>
1:0:1:0:1<br>
1:1:0:1:1<br>
0:0:1:0:0<br>
0:1:0:0:1<br>
1:0:1:0:0<br>
1:0:0:0:0<br>
1:2:2:2:1<br>
3:3:3:3:0<br>
2:1:2:1:1<br>
3:3:2:2:0<br>
3:3:2:2:1<br>

```python
for i in data:
            #sampel = 3:3:2:2:1
            pag_data=i.split(':')
            x.append(pag_data[:4]) # 3:3:2:2 = 4 The first index for x
            y.append(pag_data[4])  # 1 = Last index for y
```

## Programer
Hassan Mohammdi<br>
from❤iran 

## Image
<p align="center">
  <img src="https://github.com/HSNHK/Forecast-trading/blob/master/2020-07-31-14-14-11(1).png" width="250" title="hover text">
  <img src="https://github.com/HSNHK/Forecast-trading/blob/master/2020-07-31-14-14-04(3).png" width="250" alt="accessibility text">
</p>
