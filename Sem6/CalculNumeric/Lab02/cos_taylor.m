function s = cos_taylor(x)
  s = 0;
  t = 1;
  n = 1;
  while s + t ~= s
    s += t;
    t = -t * x^2 / (n * (n + 1));
    n += 2;
  endwhile
