using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Data.SqlClient;


namespace Seminar1_copaci
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.Title = "Prietenii padurii";
            Console.ForegroundColor = ConsoleColor.DarkMagenta;
            Console.BackgroundColor = ConsoleColor.Green;
            Console.Clear();

            string connection_string = "Server=DESKTOP-99N7EQ6;Database=Seminar1SGBD;Integrated Security=true;";

            try
            {
                using(SqlConnection connection = new SqlConnection(connection_string))
                {
                    Console.WriteLine("Starea conexiunii: {0}", connection.State);
                    connection.Open();
                    Console.WriteLine("Starea conexiunii: {0}", connection.State);

                    SqlCommand insert_command = new SqlCommand("" +
                        "INSERT INTO Copaci (soi, inaltime, data_plantarii, regiune_geo, " +
                        "nr_de_cuiburi) VALUES (@soi1, @inaltime1, @dp1, @reg1, @nr1), " +
                        "(@soi2, @inaltime2, @dp2, @reg2, @nr2);", connection);

                    insert_command.Parameters.AddWithValue("@soi1", "Brad");
                    insert_command.Parameters.AddWithValue("@inaltime1", "9");
                    insert_command.Parameters.AddWithValue("@dp1", "2000-09-10");
                    insert_command.Parameters.AddWithValue("@reg1", "Siberia");
                    insert_command.Parameters.AddWithValue("@nr1", 0);

                    insert_command.Parameters.AddWithValue("@soi2", "Palmier");
                    insert_command.Parameters.AddWithValue("@inaltime2", "12");
                    insert_command.Parameters.AddWithValue("@dp2", "1996-04-13");
                    insert_command.Parameters.AddWithValue("@reg2", "Hawai");
                    insert_command.Parameters.AddWithValue("@nr2", 1);

                    int insert_count = insert_command.ExecuteNonQuery();
                    Console.WriteLine("Numarul de copaci pe care ii avem este {0}", insert_count);

                    SqlCommand select_command = new SqlCommand("SELECT * FROM Copaci", connection);
                    SqlDataReader reader = select_command.ExecuteReader();
                    
                    if (reader.HasRows)
                    {
                        Console.WriteLine("Copacii sunt: ");
                        
                        while (reader.Read())
                        {
                            Console.WriteLine("{0} {1} {2} {3} {4} {5}",
                                reader.GetInt32(0), reader.GetString(1), reader.GetString(2),
                                reader.GetDateTime(3).ToString("dd-MM-yyyy"), reader.GetString(4),
                                reader.GetInt32(5));
                        }
                    }
                    reader.Close();

                    SqlCommand update_command = new SqlCommand("UPDATE Copaci SET nr_de_cuiburi=@nr " +
                        "WHERE regiune_geo=@regiune;", connection);
                    update_command.Parameters.AddWithValue("@nr", 5);
                    update_command.Parameters.AddWithValue("@regiune", "Hawai");
                    int update_count = update_command.ExecuteNonQuery();
                    Console.WriteLine("S-a modif numarul de cuiburi pentru {0} copaci", update_count);

                    SqlCommand delete_command = new SqlCommand("DELETE FROM Copaci WHERE " +
                        "regiune_geo=@regiune;", connection);
                    delete_command.Parameters.AddWithValue("@regiune", "Siberia");
                    int delete_count = delete_command.ExecuteNonQuery();
                    Console.WriteLine("Nr de copaci taiati este {0}", delete_count);

                    reader = select_command.ExecuteReader();
                    if (reader.HasRows)
                    {
                        Console.WriteLine("Dupa modificari copacii ramasi sunt: ");

                        while (reader.Read())
                        {
                            Console.WriteLine("{0} {1} {2} {3} {4} {5}",
                                reader.GetInt32(0), reader.GetString(1), reader.GetString(2),
                                reader.GetDateTime(3).ToString("dd-MM-yyyy"), reader.GetString(4),
                                reader.GetInt32(5));
                        }
                    }
                    reader.Close();
                }
            }
            catch (Exception ex)
            {
                Console.ForegroundColor = ConsoleColor.Red;
                Console.WriteLine("Mesajul erorii este {0}", ex.Message);
            }

            Console.ReadKey();
        }
    }
}
