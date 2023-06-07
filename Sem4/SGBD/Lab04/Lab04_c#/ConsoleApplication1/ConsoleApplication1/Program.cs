using System;
using System.Data;
using System.Data.SqlClient;
using System.Threading;

namespace ConsoleApplication1
{
    class Program
    {
        public static int count = 5;

        public static void Thread1Function()
        {
            try
            {
                using (SqlConnection con = new SqlConnection("Data Source=DESKTOP-99N7EQ6;Initial Catalog=BloodBank;Integrated Security=True;"))
                {
                    using (SqlCommand cmd = new SqlCommand("Deadlock_Thread1", con))
                    {
                        cmd.CommandType = CommandType.StoredProcedure;

                        con.Open();
                        cmd.ExecuteNonQuery();
                    }
                }
                Console.WriteLine("Thread 1 executed successfully!");
            }
            catch (SqlException ex)
            {
                Console.WriteLine("Thread 1: " + ex.Message);
                if (count > 0)
                {
                    Console.WriteLine("Thread 1 failed! Retrying Thread 1's operations...");
                    count--;
                    Thread1Function();
                }
            }
        }

        public static void Thread2Function()
        {
            try
            {
                using (SqlConnection con = new SqlConnection("Data Source=DESKTOP-99N7EQ6;Initial Catalog=BloodBank;Integrated Security=True;"))
                {
                    using (SqlCommand cmd = new SqlCommand("Deadlock_Thread2", con))
                    {
                        cmd.CommandType = CommandType.StoredProcedure;

                        con.Open();
                        cmd.ExecuteNonQuery();
                    }
                }
                Console.WriteLine("Thread 2 executed successfully!");
            }
            catch (SqlException ex)
            {
                Console.WriteLine(ex.Message);
                if (count > 0)
                {
                    Console.WriteLine("Thread 2 failed! Retrying Thread 2's operations...");
                    count--;
                    Thread2Function();
                }
            }
        }

        static void Main(string[] args)
        {
            Thread t1 = new Thread(new ThreadStart(Thread1Function));
            Thread t2 = new Thread(new ThreadStart(Thread2Function));
            Console.WriteLine("Starting Thread 1...");
            t1.Start();
            Console.WriteLine("Starting Thread 2...");
            t2.Start();
        }
    }
}