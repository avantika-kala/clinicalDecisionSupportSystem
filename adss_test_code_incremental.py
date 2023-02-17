import sys
import pandas as pd
import os
import numpy as np
import math
from math import isnan
import copy
import pyds
from pyds import MassFunction
from itertools import product
from sklearn.metrics import roc_curve, auc, roc_auc_score
import matplotlib.pyplot as plt
from sklearn import metrics
import seaborn as sns     


tpr = dict()
fpr = dict()
#specifity , sensitivity
def metv(val, y_test):
    target_class=['R', 'O', 'N', 'RU', 'U', 'UO', 'RO']
    met=[['class', 'sensitivity', 'specificity']]
    for j in range(len(target_class)):
        tn, tp, fn, fp = 0, 0, 0, 0 
        for i in range(len(val)):
            if(val[i] in target_class[j]): 
                if(y_test[i]==target_class[j]): tp+=1
                else: fp+=1
            else: 
                if(y_test[i]==target_class[j]): fn+=1
                else: tn+=1
        sen=tp/(tp+fn)
        spe=tn/(tn+fp)
        temp = [target_class[j], sen, spe]
        met.append(temp)
    return met


test = pd.read_csv(r'E:\Ph.D\3-JOURNALS\A2-ALLERGY DSS\ALLERGY DATASET\Allergy_Data2_test_uo.csv')
x_test = test.iloc[:,:-1]
y_test = test.iloc[:,-1]


for i in range(len(y_test)):
    if y_test[i]=='RH': y_test[i] = 'R'
    elif y_test[i]=='UT': y_test[i] = 'U'
    elif y_test[i]=='OT': y_test[i] = 'O'
    elif y_test[i]=='RH_UT': y_test[i] = 'RU'
    elif y_test[i]=='RH_O': y_test[i] = 'RO'
    elif y_test[i]=='UT_O': y_test[i] = 'UO'
    elif y_test[i]=='NORMAL': y_test[i] = 'N'


df = pd.read_csv(r'E:\Ph.D\3-JOURNALS\A2-ALLERGY DSS\ADSS Codes\Allergy_Data1_full_bpa.csv')
df.set_index('A/C', inplace=True)
df1 = pd.read_csv(r'E:\Ph.D\3-JOURNALS\A2-ALLERGY DSS\ADSS Codes\Allergy_Data2_full_bpa.csv')
df1.set_index('A/C', inplace=True)

list_col = list(x_test.columns)
test_bpa_full = []
for row in x_test.iterrows():
    c = 0
    test_bpa_single = []
    for a in row[1]:
        if(c == len(x_test.columns)):
            break
        if(str(a)=='KR'): 
            c+=1
            continue
        else: 
            s = str(list_col[c])+" "+str(a)
            if(s in df.index and s in df1.index):
                for i in range(len(df.loc[s])):
                    if(df.loc[s][i]==0): df.loc[s][i]+=0.0001
                for i in range(len(df1.loc[s])):
                    if(df1.loc[s][i]==0): df1.loc[s][i]+=0.0001               
                inc_bpa = dict(df.loc[s]).copy()
                inc_bpa.update(dict(df1.loc[s]))
                test_bpa_single.append(dict(inc_bpa)) 
            elif(s in df.index):
                for i in range(len(df.loc[s])):
                    if(df.loc[s][i]==0): df.loc[s][i]+=0.0001        
                test_bpa_single.append(dict(df.loc[s])) 
            elif(s in df1.index):
                for i in range(len(df1.loc[s])):
                    if(df1.loc[s][i]==0): df1.loc[s][i]+=0.0001        
                test_bpa_single.append(dict(df1.loc[s])) 
            else:
                test_bpa_single.append(dict(pd.Series([0.0001, 0.0001, 0.0001, 0.0001, 0.0001, 0.0001, 0.0001], index=['R', 'O', 'N', 'RU', 'U', 'UO', 'RO'], name=s)))
            c += 1
    test_bpa_full.append(test_bpa_single)

acc = 0
acc_dum=0
vals=[]
dps_rh=[]
dps_ut=[]
dps_ot=[]
for i in range(len(test_bpa_full)):
    initial = MassFunction(test_bpa_full[i][0])
    #print(initial)
    for j in range(1, len(test_bpa_full[i])):
        initial = initial&MassFunction(test_bpa_full[i][j])   
    #print(initial)
    pred_label = []
    sort_orders = sorted(initial.items(), key=lambda x: x[1], reverse=True)
    #print(sort_orders)
    if (str(list(sort_orders[3][0])[0])=='N'): pred_label = ['N']
    elif(initial['R']>0.8 and initial['R']<0.9):
        temp=""
        for i in range(2):
            temp+=str(list(sort_orders[i][0])[0])
        pred_label = [temp]
    else: pred_label=list(initial.max_pl())
    vals.append(pred_label[0])
    #print(pred_label)
    dps_rh.append(initial["R"])
    dps_ut.append(initial["U"])
    dps_ot.append(initial["O"])
    if(pred_label[0] in y_test[i] or y_test[i] in pred_label[0]): acc+=1
    if(pred_label[0] == y_test[i]): acc_dum+=1
print("Accuracy_soft = ", acc/len(test_bpa_full)) 
print("Accuracy_hard = ", acc_dum/len(test_bpa_full))
print(dps_ot,dps_rh,dps_ut)
plt.plot(dps_rh, marker="o", color="red",label="RH")
plt.plot(dps_ut, marker="*", color="blue",label="UT")
plt.plot(dps_ot, marker="+", color="green",label="OT")
#plt.legend(['Rhinitis','Urticaria','Others'])
plt.tick_params(axis='x', labelsize=15)
plt.tick_params(axis='y', labelsize=15)
plt.xlabel('Query number', fontweight="bold")
plt.ylabel('Decision Probability', fontweight="bold")
plt.show()

""" 
labels=["R","U","O","N","RO","UO","RU"]
print(metrics.classification_report(y_test, vals, labels))
cm = metrics.confusion_matrix(y_test, vals, labels)
print(cm)
fig = plt.figure()
ax = fig.add_subplot(111)
cax = ax.matshow(cm)
plt.title('Confusion matrix of the classifier')
fig.colorbar(cax)
ax.set_xticklabels([''] + labels)
ax.set_yticklabels([''] + labels)
plt.xlabel('Predicted')
plt.ylabel('True')
plt.show()
"""