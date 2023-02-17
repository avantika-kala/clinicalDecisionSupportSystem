import pandas as pd
import os
import numpy as np
import math
from math import isnan
from matplotlib import pyplot as plt
from sklearn.model_selection import train_test_split

######################DATA SPLITING USING STRATIFIED HOLD OUT APPROACH
"""
data = pd.read_csv(r"E:\Ph.D\3-JOURNALS\A2-ALLERGY DSS\ADSS Codes\Allergy_Data2_906.csv")
print(data['Class'].value_counts())
listhead = list(data)
train, test = train_test_split(data,test_size=0.2)
print(train['Class'].value_counts())
print(test['Class'].value_counts())
train=np.array(train)
pd.DataFrame(train).to_csv(r"E:\Ph.D\3-JOURNALS\A2-ALLERGY DSS\ADSS Codes\Allergy_Data2_ubtrain.csv")
test = np.array(test)
pd.DataFrame(test).to_csv(r"E:\Ph.D\3-JOURNALS\A2-ALLERGY DSS\ADSS Codes\Allergy_Data2_test.csv")
"""

################################CODE TO GET DATA DISTRIBUTION PLOT 
plot_dist1= pd.read_csv(r"E:\Ph.D\3-JOURNALS\A2-ALLERGY DSS\ALLERGY DATASET\Allergy_Data1_test.csv")
plot_dist2= pd.read_csv(r"E:\Ph.D\3-JOURNALS\A2-ALLERGY DSS\ALLERGY DATASET\Allergy_Data2_test.csv")
print(plot_dist1['Class'].value_counts())
print(plot_dist2['Class'].value_counts())
"""
samples1=plot_dist1['Diagnosis'].value_counts()
samples2=plot_dist2['Class'].value_counts()
samples1=dict(samples1)
samples2=dict(samples2)
plt.bar(range(len(samples1)), list(samples1.values()),0.4, align='center')
plt.bar(range(len(samples2)), list(samples2.values()),0.4, align='center')
plt.xticks(range(len(samples2)), list(samples2.keys()), fontweight='bold')
plt.yticks(fontweight='bold')
plt.xlabel("Diagnostic Decisions", fontweight='bold')
plt.ylabel("No. of Samples", fontweight='bold')
plt.title("Allergy Dataset 2", fontweight='bold')
plt.show()
"""
