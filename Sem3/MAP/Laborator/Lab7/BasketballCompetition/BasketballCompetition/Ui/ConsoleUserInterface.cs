using System;
using System.Collections.Generic;
using BasketballCompetition.Service;

namespace BasketballCompetition.Ui
{
    public class ConsoleUserInterface
    {
        private readonly AllService _service;
        private readonly Dictionary<string, Action> _commands = new Dictionary<string, Action>();

        public ConsoleUserInterface(AllService service)
        {
            _service = service;
        }

        private void AddCommands()
        {
            _commands.Add("1", UiShowTeamPlayers);
            _commands.Add("2", UiActivePlayersAtGame);
            _commands.Add("3", UiShowAllGamesInTimePeriod);
            _commands.Add("4", UiGameScore);
        }

        private void UiShowTeamPlayers()
        {
            Console.WriteLine("Enter the team name: ");
            var teamName = Console.ReadLine();

            foreach (var jucator in _service.ShowPlayersOfTeam(teamName))
            {
                Console.WriteLine("Player: " + jucator.Nume + ", Team: " + jucator.Echipa.Nume);
            }
        }

        private void UiActivePlayersAtGame()
        {
            Console.WriteLine("Enter game id: ");
            var id = Console.ReadLine();


            if (!int.TryParse(id, out var gameId))
            {
                throw new Exception("Enter an integer!");
            }
            
            Console.WriteLine("Enter the team name: ");
            var teamName = Console.ReadLine();
            

            foreach (var jucator in _service.ActivePlayersAtGame(gameId, teamName))
            {
                Console.WriteLine("Active player: " + jucator.Nume);
            }
        }

        private void UiShowAllGamesInTimePeriod()
        {
            Console.WriteLine("Enter the start of the time period: ");
            var start = Console.ReadLine();

            if (!DateTime.TryParse(start, out var dstart))
            {
                throw new Exception("Enter a date!");
            }
            
            Console.WriteLine("Enter the end of the time period: ");
            var end = Console.ReadLine();

            if (!DateTime.TryParse(end, out var dend))
            {
                throw new Exception("Enter a date!");
            }

            foreach (var meci in _service.ShowAllGamesInTimePeriod(dstart, dend))
            {
                Console.WriteLine("Team1: " + meci.Echipa1 + ", Team2: " + meci.Echipa2 +
                                  ", date: " + meci.Date);
            }
        }

        private void UiGameScore()
        {
            Console.WriteLine("Enter game id: ");
            var id = Console.ReadLine();

            if (!int.TryParse(id, out var gameId))
            {
                throw new Exception("Enter an integer!");
            }

            var resTeams = _service.GameTeams(gameId);
            var resScore = _service.GameScore(gameId);
            Console.WriteLine("Team " + resTeams.Item1 + " score: " + resScore.Item1 + "\n"
                              + "Team " + resTeams.Item2 + " score: " + resScore.Item2);
        }
        
        public void Run()
        {
            AddCommands();

            while (true)
            {
                try
                {
                    Console.WriteLine("\nClose app: quit");
                    Console.WriteLine("Commands:");
                    Console.WriteLine("1: Show all players of a team");
                    Console.WriteLine("2: Show all active players of a team at a game");
                    Console.WriteLine("3: Show all games in a period of time");
                    Console.WriteLine("4: Show the score of a game");

                    var cmd = Console.ReadLine();

                    if (cmd != null && cmd.Equals("quit"))
                    {
                        return;
                    }

                    if (cmd != null && _commands.ContainsKey(cmd))
                    {
                        _commands[cmd]();
                    }
                    else
                    {
                        Console.WriteLine("This command does not exist!\n");
                    }
                }
                catch (Exception e)
                {
                    Console.WriteLine(e.Message);
                }
            }
        }
    }
}