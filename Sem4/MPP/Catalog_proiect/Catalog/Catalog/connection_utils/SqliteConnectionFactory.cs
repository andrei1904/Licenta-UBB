using System.Data;
using System.Data.SQLite;

namespace Catalog.connection_utils
{
    public class SqliteConnectionFactory : ConnectionFactory
    {
        public override IDbConnection CreateConnection()
        {

            var conStr = "Data Source=C:\\Fac\\proiect\\Catalog\\catalog_bazadedate.db;Version=3";
            return new SQLiteConnection(conStr);
        }
    }
}
