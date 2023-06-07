using System;
using System.Collections.Generic;
using System.Linq;
using BasketballCompetition.Domain;

namespace BasketballCompetition.Service
{
    public class AllService
    {
        private readonly ServiceEchipa _serviceEchipa;
        private readonly ServiceJucator _serviceJucator;
        private readonly ServiceMeci _serviceMeci;
        private readonly ServiceJucatorActiv _serviceJucatorActiv;

        public AllService(ServiceEchipa serviceEchipa, ServiceJucator serviceJucator, ServiceMeci serviceMeci, ServiceJucatorActiv serviceJucatorActiv)
        {
            _serviceEchipa = serviceEchipa;
            _serviceJucator = serviceJucator;
            _serviceMeci = serviceMeci;
            _serviceJucatorActiv = serviceJucatorActiv;
        }

        public IEnumerable<Jucator> ShowPlayersOfTeam(string teamName)
        {
            var team = _serviceEchipa.FindOneByName(teamName);

            var players = _serviceJucator.FindOneByTeam(team);

            return players;
        }


        public List<Meci> ShowAllGamesInTimePeriod(DateTime dstart, DateTime dend)
        {
            var teams = _serviceMeci.FindAll();

            var res = teams.Where(x =>
                    DateTime.Compare(dstart, x.Date) <= 0 &&
                    DateTime.Compare(x.Date, dend) <= 0)
                .ToList();

            return res;
        }

        public List<Jucator> ActivePlayersAtGame(int gameId, string teamName)
        {
            var game = _serviceMeci.FindOne(gameId);

            var teams = _serviceEchipa.FindAll();

            if (!teams.Select(x => x.Nume).Contains(teamName))
            {
                throw new Exception("This team doesen't exist!");
            }
            
            if (game.Echipa1.Nume != teamName && game.Echipa2.Nume != teamName)
            {
                throw new Exception("This team did not participate in the game!");
            }

            var players = ShowPlayersOfTeam(teamName);

            var activePlayers = _serviceJucatorActiv.FindAllActiveByGame(game);
            var activePlayersIds = activePlayers.Select(x => x.IdJucator).ToList();

            var res = players
                .Where(x => activePlayersIds.Contains(x.Id))
                .ToList();
            
            return res;
        }

        public Tuple<int, int> GameScore(int gameId)
        {
            var game = _serviceMeci.FindOne(gameId);
            
            var players1 = ShowPlayersOfTeam(game.Echipa1.Nume);
            var players1Ids = players1.Select(x => x.Id);
            
            var players2 = ShowPlayersOfTeam(game.Echipa2.Nume);
            var players2Ids = players2.Select(x => x.Id);

            var playersOfGame = _serviceJucatorActiv.FindAllActiveByGame(game);

            var res1 = playersOfGame
                .Where(x => players1Ids.Contains(x.IdJucator))
                .Select(x => x.NrPuncteInscrise)
                .Sum();

            var res2 = playersOfGame
                .Where(x => players2Ids.Contains(x.IdJucator))
                .Select(x => x.NrPuncteInscrise)
                .Sum();
            
            return new Tuple<int, int>(res1, res2);
        }

        public Tuple<string, string> GameTeams(int gameId)
        {
            var game = _serviceMeci.FindOne(gameId);
            
            return new Tuple<string, string>(game.Echipa1.Nume, game.Echipa2.Nume);
        }
    }
}