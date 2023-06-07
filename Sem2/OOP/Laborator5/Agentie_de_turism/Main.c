#define _CRTDBG_MAP_ALLOC

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "Console.h"
#include "Model.h"
#include "Validation.h"
#include "Repo.h"
#include "Service.h"


void test_all() {
    test_create_destroy();
    test_validate();
    test_create_list();
    test_iterate_list();
    test_copy_list();
    test_resize();
    test_add_ofert();
    test_modify_ofert();
    test_delete_ofert();
    test_order_ofert();
    test_filter_oferts();
    test_lista_de_liste();
    test_undo();
}

int main() {
    test_all();
    run();

    return 0;
}