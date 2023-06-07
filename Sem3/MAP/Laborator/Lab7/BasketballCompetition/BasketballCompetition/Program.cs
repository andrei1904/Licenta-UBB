using BasketballCompetition.Domain.Validators;
using BasketballCompetition.Repository.File;
using BasketballCompetition.Service;
using BasketballCompetition.Ui;

namespace BasketballCompetition
{
    internal static class Program
    {
        private static void Main()
        {
            var validatorTeam = new EchipaValidator();
            var teamFileRepo = new EchipaFileRepository("C:\\Fac\\Sem3\\MAP\\Laborator\\Lab7\\BasketballCompetition\\BasketballCompetition\\Data\\teams.csv", validatorTeam);
            
            var validatorPlayer = new JucatorValidator();
            var playerFileRepo = new JucatorFileRepository("C:\\Fac\\Sem3\\MAP\\Laborator\\Lab7\\BasketballCompetition\\BasketballCompetition\\Data\\players.csv", validatorPlayer);

            // var validatorStudent = new ElevValidator();
            // var studentFileRepo = new ElevFileRepository("C:\\Fac\\Sem3\\MAP\\Laborator\\Lab7\\BasketballCompetition\\BasketballCompetition\\Data\\students.csv", validatorStudent);
            
            var validatorMeci = new MeciValidator();
            var meciFileRepo = new MeciFileRepository("C:\\Fac\\Sem3\\MAP\\Laborator\\Lab7\\BasketballCompetition\\BasketballCompetition\\Data\\games.csv", validatorMeci);
            
            var validatorJucatorActiv = new JucatorActivValidator();
            var jucatorActivFileRepo = new JucatorActivFileRepository("C:\\Fac\\Sem3\\MAP\\Laborator\\Lab7\\BasketballCompetition\\BasketballCompetition\\Data\\activeplayers.csv", validatorJucatorActiv);
            
            var serviceTeam = new ServiceEchipa(teamFileRepo);
            // var serviceStudent = new ServiceElev(studentFileRepo);
            var servicePlayer = new ServiceJucator(playerFileRepo);
            var serviceMeci = new ServiceMeci(meciFileRepo);
            var serviceJucatorActiv = new ServiceJucatorActiv(jucatorActivFileRepo);
            
            var allService = new AllService(serviceTeam, servicePlayer, 
                serviceMeci, serviceJucatorActiv);
            
            var userInterface = new ConsoleUserInterface(allService);
            
            userInterface.Run();
        }

    }
}