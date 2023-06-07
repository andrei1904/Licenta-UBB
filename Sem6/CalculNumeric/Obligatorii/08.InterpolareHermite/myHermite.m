function [hermiteCoeffs] = myHermite(nodes,fnodes,dnodes)
    m=length(nodes);
    z=zeros(1,2*m);
    q=zeros(2*m);
    for i=0:m-1   
       z(2*i+1)=nodes(i+1); 
       z(2*i+2)=nodes(i+1);
       q(2*i+1,1)=fnodes(i+1);
       q(2*i+2,1)=fnodes(i+1);
       q(2*i+1,2)=dnodes(i+1);
       
       if i~=0
           q(2*i,2)=(q(2*i+1,1)-q(2*i,1))/(z(2*i+1)-z(2*i));
       end         
    end
    for j=2:2*m-1
        for i=1:2*m-j
            q(i,j+1)=(q(i+1,j)-q(i,j))/(z(j+i)-z(i));
        end    
    end
    syms x H(x) p(x)
    diffdiv=q(1,:);
    H(x)=diffdiv(1);
    p(x)=(x-nodes(1));
    for i=2:2*m
        H(x)=H(x)+diffdiv(i)*p(x);
        p(x)=p(x)*(x-nodes(floor((i+1)/2)));
    end
    H(x)=expand(H(x));
    hermiteCoeffs = eval(coeffs(H(x)));
    hermiteCoeffs=flip(hermiteCoeffs);
end
