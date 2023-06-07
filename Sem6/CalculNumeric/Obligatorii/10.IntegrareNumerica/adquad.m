function [Q,fcount] = adquad(F,a,b,tol,varargin)

    if ischar(F) && exist(F)~=2
       F = inline(F);
    elseif isa(F,'sym')
       F = inline(char(F));
    end 
    
    if nargin < 4 || isempty(tol), tol = 1.e-6; end
    
    c = (a + b)/2;
    fa = F(a,varargin{:}); fc = F(c,varargin{:});
    fb = F(b,varargin{:});
    
    [Q,k] = quadstep(F, a, b, tol, fa, fc, fb, varargin{:});
    fcount = k + 3;
end


