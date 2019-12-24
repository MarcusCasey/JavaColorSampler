%1
%predicate reverse (L, L1) 
%append list head with reversed tail
reverse([], []).
reverse([X], [X]). 
reverse([H|T], Y) :- reverse(T,T1), append(T1, [H], Y).

 
%2
%predicate take(L, N, L1) if L1 = first element of L  
%take(L, N, L1)

take([], _, []). 
take(_, 0, []).
take([H|T], N, [H1|T1]) :- H=H1, take(T, X, T1), N is X+1.

 

%predicate nleaves(T,N). if N=number of leaves in T 
%3A

nLeaves(node(_, nil, nil), N) :- N is 1. 
nLeaves(node(_, L, nil), N) :- nLeaves(L, Nl), N is Nl. 
nLeaves(node(_, nil, R), N) :- nLeaves(R, Nr), N is Nr. 
nLeaves(node(_, L, R), N) :- nLeaves(L, Nl), nLeaves(R,Nr), N is (Nl + Nr).

 

%predicate treeMember(E,T), if E appears as an element in the T
%3b

treeMember(E, node(E, _, _)). %Case 1: E is a member
treeMember(E, node(_, L, R)) :- treeMember(E, L); treeMember(E, R). 



%predicate preOrder(T,L). if L with all in T correspond to a pre-order %traversal of the tree
%3c

preOrder(nil, []).
preOrder(node(X, L, R), [X|T]) :- preOrder(L, Ll), append(Ll, Lr, T), preOrder(R, Lr).

 

% height =  max number of nodes on a path from root to leaf. 
% predicate height(T, N) succeeds if N is the height of tree T. 
$3d

height(nil, N) :- N is 0. 
height(node(_, L, R), N) :- height(L, Nl), height(R, Nr), N is (1+max(Nl, Nr)).

 


%4

%predicate insert(X,L,L1). if list L1 is == to L with X at specific place. Insert X in ordered list L. L1 =  L with X inserted insert(X, [], [X]).

insert(X, [H|T], [X,H|T]) :- X < H, !.
insert(X, [H|T0], [H|T]) :- insert(X, T0, T).

 

%5

% predicate flatten(A,B) If A and B with all elements of A and sublists are at the same level

flatten([], []) :- !.
flatten([H|T], B) :- !, flatten(H, C), flatten(T, D), append(C, D, B).
flatten(A, [A]).