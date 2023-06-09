﻿using System.Data;
using Catalog.connection_utils;

namespace Catalog.repository
{
    class DbUtils
    {
        private static IDbConnection _instance = null;

        public static IDbConnection GetConnection()
        {
            if (_instance == null || _instance.State == ConnectionState.Closed)
            {
                _instance = GetNewConnection();
                _instance.Open();
            }

            return _instance;
        }


        private static IDbConnection GetNewConnection()
        {
            return ConnectionFactory.GetInstance().CreateConnection();
        }
    }
}
