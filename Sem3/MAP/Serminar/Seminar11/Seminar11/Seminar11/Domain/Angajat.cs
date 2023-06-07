using System;
using System.Collections.Generic;
using System.Text;

namespace Seminar11.Domain
{
    enum KnowledgeLevel
    {
        Junior, Medium, Senior
    }

    class Angajat : Entity<string>
    {
        //private String angajatId;
        //public string AngajatId
        //{
        //    get { return angajatId; }
        //    set { angajatId = value; }
        //}

        public String Nume { get; set; }

        public float VenitPeOra { get; set; }

        public KnowledgeLevel Nivel { get; set; }

        public override string ToString()
        {
            return Id + " " + Nume + " " + VenitPeOra + " " + Nivel;
        }
    }
}
