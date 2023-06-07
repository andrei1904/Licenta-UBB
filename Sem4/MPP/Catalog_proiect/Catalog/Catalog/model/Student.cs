namespace Catalog.model
{
    class Student
    {
        public int NrMatricol { get; set; }
        public string Nume { get; set; }
        public string Prenume { get; set; }

        public Student(int NrMatricol, string Nume, string Prenume)
        {
            this.NrMatricol = NrMatricol;
            this.Nume = Nume;
            this.Prenume = Prenume;
        }
    }
}
