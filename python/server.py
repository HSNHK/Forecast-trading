from flask import Flask,request,jsonify
from sklearn import tree
from datetime import datetime
import os

app=Flask(__name__)

def mining():
    x=[]
    y=[]
    try:
        with open('data.txt') as file:
            data=file.readlines()   
        for i in data:
            pag_data=i.split(':')
            x.append(pag_data[:4])
            y.append(pag_data[4].replace('\n',''))
            
        clf=tree.DecisionTreeClassifier()
        clf.fit(x,y)
        for_check = data[len(data)-1].split(':')
        minid=clf.predict([for_check[:4]])
        return {"m":minid[0],"l":len(data)}
    except:
        return {"m":'error',"l":'error'}

@app.route('/get')
def show():
    if os.path.isfile("data.txt"):
        return jsonify({"status":mining()["m"],"len":str(mining()["l"]),"time":str(datetime.today().strftime('%Y-%m-%d'))})
    else:
        return 'not data for mining !!!'

@app.route('/add',methods=['POST'])
def add():
    try:
        data=request.get_json()
        all_data=data['data1']+":"+data['data2']+":"+data['data3']+":"+data['data4']+":"+data['data5']+"\n"
        with open('data.txt','a') as file:
            file.writelines(all_data)
        return 'ok'
    except:
        return 'no'

if __name__=="__main__":
    app.run(host='0.0.0.0')
