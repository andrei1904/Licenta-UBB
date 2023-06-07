function [ val ] = ex04( x, f, m, nodes )
    considered = nodes(1 : m);
    consideredVals = f(considered);
    
    val = ex01_interpolare(considered, consideredVals, x);
end
