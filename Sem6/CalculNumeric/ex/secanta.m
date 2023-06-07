function p = secanta(f, p0, p1, e, n)
    q0 = f(p0);
    q1 = f(p1);
    for i = 2:n
        p = eval(p1 - (q1 * (p1 - p0)) / (q1 - q0));
        if abs(p - p1) < e
            return
        end
        p0 = p1;
        q0 = q1;
        p1 = p;
        q1 = f(p);
    end
    %error('precizia nu poate fi atinsa cu numarul dat de iteratii');
end