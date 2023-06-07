namespace Catalog.model
{
    class Disciplina
    {
        public string CodDisciplina { get; set; }
        public string Denumire { get; set; }
        public int NrCredite { get; set; }

        public Disciplina(string CodDisciplina, string Denumire, int NrCredite)
        {
            this.CodDisciplina = CodDisciplina;
            this.Denumire = Denumire;
            this.NrCredite = NrCredite;
        }


    }
}
