import http.client as http
import urllib

conn = http.HTTPSConnection("projecteuler.net")
data = urllib.parse.urlencode({"username":"wilde.jagd", "password":"noncomprehendingly"})
print(data)
afji
conn.request("POST", "/login", data)

response = conn.getresponse().read().decode()

print(response)
