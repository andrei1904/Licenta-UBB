function rez = cos_taylor_red(x)
  twoOverPi = sym(2) / sym(pi);
  y = sym(x) * vpa(twoOverPi, 100);
  k = floor(y);
  r = double((y - k) * pi / 2);
  rest = mod(k, 4);
 
  if rest == 0
    rez = cos_taylor(r);
  elseif rest == 1
    rez = -sin_taylor(r);
  elseif rest == 2
    rez = -cos_taylor(r);
  else
    rez = sin_taylor(r);
  end