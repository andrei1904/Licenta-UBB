#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <assert.h>
#include "Validation.h"
#include "Model.h"
//tip (munte,mare, city break), destinatie, data plecare, pret DD-MM-YYYY


int validate_ofert(Ofert* o)
{

    if (strcmp(get_type(o), "munte") != 0 &&
        strcmp(get_type(o), "mare") != 0 &&
        strcmp(get_type(o), "city break") != 0)
        return 1;
    //validate date---
    int months[13] = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
    if (get_date(o).yy <= 0 || get_date(o).yy > 9999)
        return 2;
    else if (get_date(o).mm <= 0 || get_date(o).mm > 12)
        return 2;
    else
    {
        if (get_date(o).yy % 4 == 0)
            months[2] += 1;
        if (get_date(o).dd <= 0 || get_date(o).dd > months[get_date(o).mm])
            return 2;
    }
    if (strlen(get_destination(o)) == 0)
        return 3;
    if (get_price(o) <= 0)
        return 4;
    return 0;
}



void test_validate()
{
    Date d = { 56,5,32 };
    Ofert* o = create_ofert("padure", "madagascar", d, 342);
    assert(validate_ofert(o)==1);
    strcpy(o->type, "munte");
    assert(validate_ofert(o) == 2);
    Date d1 = { 29, 2, 2020 };
    o->date = d1;
    strcpy(o->destination, "");
    assert(validate_ofert(o) == 3);
    strcpy(o->destination, "aruba");
    o->price = -4;
    assert(validate_ofert(o) == 4);
    o->price = 2;
    assert(validate_ofert(o) == 0);
    Date d2 = { 5,13,2005 };
    set_date(o, d2);
    assert(validate_ofert(o) == 2);
    d2.mm = 5;
    d2.yy = 10000;
    set_date(o, d2);
    assert(validate_ofert(o) == 2);
    d2.yy = 2005;
    set_date(o, d2);
    assert(validate_ofert(o) == 0);
    destroy_ofert(o);
}