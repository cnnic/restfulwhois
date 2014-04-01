# -*- coding: utf-8 -*-
from urllib import quote

class UrlEncode:
    def encode_url(self, str):
        safe = ":?=/&#"
        return quote(str.encode('utf-8'),safe)
    def decode_data(self, str):
        return str.decode('utf-8')