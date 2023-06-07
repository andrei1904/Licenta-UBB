using System;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Threading.Tasks;
using Domain.model;
using Newtonsoft.Json.Linq;

namespace Rest
{
    internal class Program
    {
        private class Race
        {
            public int id { get; set; }
            public string name { get; set; }
            public EngineCapacity requiredEngineCapacity { get; set; }

            public Race(string name, EngineCapacity requiredEngineCapacity)
            {
                this.name = name;
                this.requiredEngineCapacity = requiredEngineCapacity;
            }
        }
        
        private static readonly HttpClient Client = new HttpClient();
        
        public static void Main(string[] args)
        {
            RunAsync().Wait();
        }

        static async Task RunAsync()
        {
            Client.BaseAddress = new Uri("http://localhost:8080/contest/races");
            Client.DefaultRequestHeaders.Accept.Clear();
            Client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));

            var race = await GetRaceAsync("http://localhost:8080/contest/races/1");
            Console.WriteLine("Race with id 1: \n " + race.id + ": " + race.name + ", " +
                              race.requiredEngineCapacity);

            race = new Race("RaceRace1", EngineCapacity.EngineCapacity250Cc);
            race = await SaveRaceAsync("http://localhost:8080/contest/races", race);
            
            var races = await GetAllRacesAsync("http://localhost:8080/contest/races");
            Console.WriteLine("\nAll races:");
            foreach (var race1 in races)
            {
                Console.WriteLine(race1.id + ": " + race1.name + ", " +
                                  race1.requiredEngineCapacity);
            }
            
            race = new Race("Race10000001", EngineCapacity.EngineCapacity125Cc);
            var res = await GetAllRacesAsync("http://localhost:8080/contest/races");
            var id = res[res.Length - 1].id;
            race = await UpdateRaceAsync("http://localhost:8080/contest/races/" + id.ToString(), race);

            race = await GetRaceAsync("http://localhost:8080/contest/races/" + id.ToString());
            Console.WriteLine("Update: " + race.name);
            
            race = await DeleteRaceAsync("http://localhost:8080/contest/races/Race10000001");
        }

        static async Task<Race> GetRaceAsync(string path)
        {
            Race race = null;
            var response = await Client.GetAsync(path);
            if (response.IsSuccessStatusCode)
            {
                race = await response.Content.ReadAsAsync<Race>();
            }

            return race;
        }
        
        static async Task<Race[]> GetAllRacesAsync(string path)
        {
            Race[] races = null;
            var response = await Client.GetAsync(path);
            if (response.IsSuccessStatusCode)
            {
                races = await response.Content.ReadAsAsync<Race[]>();
            }

            return races;
        }

        static async Task<Race> SaveRaceAsync(string path, Race race)
        {
            Race result = null;
            HttpResponseMessage responseMessage = await Client.PostAsJsonAsync<Race>(path, race);
            if (responseMessage.IsSuccessStatusCode)
            {
                result = await responseMessage.Content.ReadAsAsync<Race>();
            }

            return result;
        }
        
        static async Task<Race> UpdateRaceAsync(string path, Race race)
        {
            Race result = null;
            HttpResponseMessage responseMessage = await Client.PutAsJsonAsync<Race>(path, race);
            if (responseMessage.IsSuccessStatusCode)
            {
                result = await responseMessage.Content.ReadAsAsync<Race>();
            }

            return result;
        }
        
        static async Task<Race> DeleteRaceAsync(string path)
        {
            Race result = null;
            HttpResponseMessage responseMessage = await Client.DeleteAsync(path);
            if (responseMessage.IsSuccessStatusCode)
            {
                result = await responseMessage.Content.ReadAsAsync<Race>();
            }

            return result;
        }
    }
}