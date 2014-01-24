# coding=UTF-8
import httplib
import urllib

class TestData:
    def __init__(self, path, acceptHeader, params, responseStatus, responseMark):
        self.path = path
        self.acceptHeader = acceptHeader
        paramsMap = {}
        if(params != ""):
            paramsArray = params.split("&")
            for param in paramsArray:
                kV = param.split("=")
                key = ""
                if(len(kV) >= 1):
                    key = kV[0]
                value = ""
                if(len(kV) >= 2):
                    value = kV[1]
                paramsMap[key] = value
        self.paramsMap = paramsMap
        self.params = params
        self.responseStatus = responseStatus
        self.responseMark = responseMark
class Response:
    def __init__(self, status, data):
        self.status = status
        self.data = data
def send(testData, address, new):
    conn = httplib.HTTPConnection(address)
    params = urllib.urlencode(testData.paramsMap)
    if(new == 0):
        path = testData.path
    else:
        path = "/.well-known/rdap"+testData.path
    if(len(testData.paramsMap) >0 ):
        path = path+"?"+params
    conn.request("GET", path, headers={"Accept":testData.acceptHeader})
#     conn.request("GET", testData.path, body=params, headers={"Accept":testData.acceptHeader})
    r1 = conn.getresponse()
    data = r1.read()
    return Response(r1.status, data)
def getTestData(line):
    cols = line.split(",")
    print "path:" + cols[0] + ",accept:" + cols[1] + ",param:" + cols[2] + ",status:" + cols[3] + ",mark:" + cols[4]
    return TestData(cols[0], cols[1], cols[2], cols[3], cols[4])
def sendAll(filePath, address1, address2):
    f = file(filePath)
    f.readline()  # skip title
    while True:
        line = f.readline().replace('\n', '')
        if(len(line) == 0):
            break
        if(line.startswith("#")):
            continue
        testData = getTestData(line)
        res1 = send(testData, address1, 0)
        res2 = send(testData, address2, 1)
        if(str(res1.status) == testData.responseStatus):
            print "    " + address1 + "    status ok."
        else:
            print "    " + address1 + "    invalid status:"+str(res1.status)
        if(str(res2.status) == testData.responseStatus):
            print "    " + address2 + "    status ok."
        else:
            print "    " + address2 + "    invalid status:"+str(res2.status)
        if(testData.responseMark != ""):
            if(str(res1.data.lower()).find(testData.responseMark.lower()) > 0):
                print "    " + address1 + "    data ok."
            else:
                print "    " + address1 + "    invalid data:"#+str(res1.data)
            if(str(res2.data.lower()).find(testData.responseMark.lower()) > 0):
                print "    " + address2 + "    data ok."
            else:
                print "    " + address2 + "    invalid data:"#+str(res2.data)
        else:
            print "    no mark."

        print "-------------------------------------------------------------------------------------"
# data-search.csv    data-query.csv
#218.241.108.82:8080    localhost:8080
sendAll("data-search.csv", "218.241.108.82:8080", "localhost:8080")
