function [ val ] = evaluare_polinom_fundamental_puncte_multiple( nodes, k, x )
    val = zeros(size(x));
    [~, c] = size(x);
    for i = 1 : c 
       val(1, i) = evaluare_polinom_fundamental(nodes, k, x(1, i)); 
    end
end