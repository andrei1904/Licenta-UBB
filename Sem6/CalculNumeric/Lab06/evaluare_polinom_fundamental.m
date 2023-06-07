function [ val ] = evaluare_polinom_fundamental(nodes, k, x )
    [~, m] = size(nodes);
    u = 1; d = 1;
    for j = 1 : m
       if j ~= k
          u = u * (x - nodes(1, j));
          d = d * (nodes(1, k) - nodes(1, j));
       end
    end
    val = u / d;
end
