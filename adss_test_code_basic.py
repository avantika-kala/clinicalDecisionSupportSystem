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


test = pd.read_csv(r'E:\Ph.D\3-JOURNALS\A2-ALLERGY DSS\ALLERGY DATASET\Allergy_Data2_test.csv')
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


df = pd.read_csv(r'E:\Ph.D\3-JOURNALS\A2-ALLERGY DSS\ADSS Codes\Allergy_Data2_full_bpa.csv')
df.set_index('A/C', inplace=True)

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
            if(s in df.index):
                for i in range(len(df.loc[s])):
                    if(df.loc[s][i]==0): df.loc[s][i]+=0.0001
                test_bpa_single.append(dict(df.loc[s]))
            else:
                test_bpa_single.append(dict(pd.Series([0.0001, 0.0001, 0.0001, 0.0001, 0.0001, 0.0001, 0.0001], index=['R', 'O', 'N', 'RU', 'U', 'UO', 'RO'], name=s)))
            c += 1
    test_bpa_full.append(test_bpa_single)
    break
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
    print(initial)
    

