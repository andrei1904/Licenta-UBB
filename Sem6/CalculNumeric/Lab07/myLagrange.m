function Lmf=myLagrange(points,nodes,values)
    syms x y Lmf(x) lk(x)
    Lmf(x)=0;
    m=length(nodes);
    for i=1:m
        lk(x)=1;
        for j=1:m
            if j~=i
                lk(x)=lk(x)*(x-nodes(j))/(nodes(i)-nodes(j));
            end
        end
        Lmf(x)=Lmf(x)+(lk(x)*values(i));  

    end
    Lmf(x)=expand(Lmf(x));
    
    for i=1:length(points)
        fprintf('Aproximare folosind interpolare Lagrange f(%d)=%s.\n',points(i),eval(Lmf(points(i))));
    end
end
