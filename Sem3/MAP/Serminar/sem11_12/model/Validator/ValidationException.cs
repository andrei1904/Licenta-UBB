﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Sem11_12.Model.Validator
{
    class ValidationException : ApplicationException
    {
        public ValidationException(String message): base(message)
        {
        }
    }
}
