#!/usr/bin/python

from multiprocessing import *
import os
import time

def fibonacci():
    a, b = 0, 1
    while a <= 144:
        print("Fibonacci: {}, Process ID: {}".format(a, os.getpid()))
        time.sleep(1)
        a, b = b, a + b

def exponential():
    num = 1
    while num <= 1024:
        print("Exponential: {}, Process ID: {}".format(num, os.getpid()))
        time.sleep(1)
        num *= 2

print("This is the main process with ID: {}".format(os.getpid()))

def procSample():
    print("This is the sub-process with ID: {}".format(str(os.getpid())))

p1 = Process(target = fibonacci)
p1.start()

p2 = Process(target = exponential)
p2.start()

p1.join()
p2.join()

print("We are done")