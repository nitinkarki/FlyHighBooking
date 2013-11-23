#!/usr/bin/python

import random

loccities = ["Phoenix","San Diego","Ontario","Burbank","Los Angeles","Orange County","Las Vegas","San Jose","San Francisco","Oakland"]
forcities = ["Phoenix","London","Rome","Frankfurt","Tokyo","Manila","Madrid","Beijing","Shanghai","Paris","Barcelona","Hong Kong","Singapore","Cancun","Montreal","Istanbul","Munich","Amsterdam","Dubai","Toronto","Mumbai"]

def combinate(l,b):
	s = ""
	for i in l:
		for j in l:
			if i is not j:
				for k in range(random.randrange(1,5)):
					s = s + "%s\t%s\t%d\t%d:%s\n" % (i,j,random.randrange(b,b+400),random.randrange(0,23),"{0:02}".format(random.randrange(0,59)));
#					s = s + "{\"%s\", \"%s\", \"%d\", \"%d:%s\"},\n" % (i,j,random.randrange(b,b+400),random.randrange(0,23),"{0:02}".format(random.randrange(0,59)));
	return s

#print combinate(loccities,100)
#print
#print combinate(loccities,300)
#print
#print combinate(forcities,600)
#print
print combinate(forcities,1000)

