# coding=utf-8
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
def send(testData):
#     conn = httplib.HTTPConnection("localhost:8080")
    conn = httplib.HTTPConnection("rdap.restfulwhois.org")
#     conn = httplib.HTTPConnection("218.241.108.82:8080")
    #218.241.108.82:8080
    params = urllib.urlencode(testData.paramsMap)
    path = testData.path
    if(len(testData.paramsMap) >0 ):
        path = path+"?"+urllib.urlencode(testData.paramsMap)
    conn.request("GET", path, headers={"Accept":testData.acceptHeader})
#     conn.request("GET", testData.path, body=params, headers={"Accept":testData.acceptHeader})
    r1 = conn.getresponse()
    data = r1.read()
    return Response(r1.status, data)
def getTestData(line):
    cols = line.split(",")
    print "path:" + cols[0] + ",accept:" + cols[1] + ",param:" + cols[2] + ",status:" + cols[3]
    return TestData(cols[0], cols[1], cols[2], cols[3], cols[4])
def sendAll(filePath):
    f = file(filePath)
    f.readline()  # skip title
    while True:
        line = f.readline().replace('\n', '')
        if(len(line) == 0):
            break
        if(line.startswith("#")):
            continue
        testData = getTestData(line)
        res = send(testData)
        if(str(res.status) == testData.responseStatus):
            print "    status ok."
        else:
            print "    invalid status:"+str(res.status)
            print "**********************************************"
        if(testData.responseMark != ""):
            marks = testData.responseMark.split(";")
            for mark in marks:
                mark = str(mark)
                if(str(res.data).find(mark) > 0):
                    print "    mark ok for : " + mark
                else:
                    print "    mark error for: " + mark
                    print "    **********************************************"
                    print "    " + str(res.data)
                    break
        else:
            print "    no mark."
# sendAll("data-search.csv")
sendAll("data-search.csv")