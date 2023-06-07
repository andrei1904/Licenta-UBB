function [z,ni]=MetAproxSuccesive(f,fd,x0,ea,er,nmax)

if nargin < 6, nmax=50; end
if nargin < 5, er=0; end
if nargin < 4, ea=1e-3; end

fi = @(x) x - fd(x0) \ f(x);

xp=x0(:);   %previous x
for k=1:nmax
    xc=fi(xp);
    if norm(xc-xp,inf)<ea+er*norm(xc,inf)
        z=xc; %success
        ni=k;
        return
    end
    xp=xc;
end

error('maximum iteration number exceeded')
