#!/usr/bin/env python
# -*- coding: UTF-8 -*-
#

import _mysql
import platform

DB_HOST = "localhost"
DB_NAME = "mysql"
DB_USERNAME = "root"
DB_PASSWORD = ""

EXPORT_TABLES = ["db","event","host"]

if platform.system() == "Windows":
    EXPORT_TEMP = "c:"
else:
    EXPORT_TEMP = "/tmp"
    
EXPORT_PACAGE = "com.mbaobao.msku"


'''
    Programer main
'''

db = _mysql.connect(DB_HOST,DB_USERNAME,DB_PASSWORD,DB_NAME)
print 'db connection success'


def query_table_schema(table_name):
    u'''查询表结构信息
    '''
    SQL = """SELECT
                COLUMN_NAME,
                DATA_TYPE, IS_NULLABLE,
                CHARACTER_MAXIMUM_LENGTH,
                NUMERIC_PRECISION
            FROM 
                INFORMATION_SCHEMA.COLUMNS
            WHERE
                table_name='%s' AND table_schema='%s'"""%(table_name,DB_NAME)

    
    

db.query("show tables")
r = db.store_result()







