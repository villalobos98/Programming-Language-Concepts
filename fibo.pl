% First value in Fibonacci sequence is at position 1
ffibo( 0, 1 ). 
ffibo( 1, 2 ).

ffibo( F, N ) :- N > 2,
                 M is N-1, ffibo( J, M ), L is N-2, ffibo( K, L ),
                 F is K + J.
fibo( F, N ) :- number( N ), ffibo( F, N ).
fibo( F, N ) :- number( F ), rfibo( F, N ).

rfibo( 0, 1, 2 ). 
% 3 parameter fibonacci sequence
rfibo(P,F,N):-
    P > 0,
    J is F-P,
    rfibo(J,P,M),
    N is M+1.

rfibo(0,1).
rfibo(1,2).
% 2 parameter fibonacci sequence
rfibo(F, N):-
    less_than(P, F),
    rfibo(P, F, N).
    

% The less_than helper function
less_than(X, Y):- 
    Y > 1, 
    X is Y - 1.
less_than(X, Y):-
    Y > 1,
    B is Y - 1,
    less_than(X, B).


