using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Catalog.model
{
    class Nota
    {
        public int numar_matricol { get; set; }
        public string cod_disciplina { get; set; }
        public int nota { get; set; }

        public Nota(int numar_matricol, string cod_disciplina, int nota)
        {
            this.numar_matricol = numar_matricol;
            this.cod_disciplina = cod_disciplina;
            this.nota = nota;
        }
    }
}
