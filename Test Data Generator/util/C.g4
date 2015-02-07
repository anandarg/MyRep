/*
 [The "BSD licence"]
 Copyright (c) 2013 Sam Harwell
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions
 are met:
 1. Redistributions of source code must retain the above copyright
    notice, this list of conditions and the following disclaimer.
 2. Redistributions in binary form must reproduce the above copyright
    notice, this list of conditions and the following disclaimer in the
    documentation and/or other materials provided with the distribution.
 3. The name of the author may not be used to endorse or promote products
    derived from this software without specific prior written permission.

 THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

/** C 2011 grammar built from the C11 Spec */
grammar C ;

primaryExpression
    :   Identifier #identifier
    |   Constant #constant
    |   StringLiteral+ #string
    |   '(' expression ')' #expr
    |   genericSelection #genericSelectionExpr
    |   '__extension__'? '(' compoundStatement ')' #compoundExpression // Blocks (GCC extension) 
    |   '__builtin_va_arg' '(' unaryExpression ',' typeName ')' #builtInVaArg
    |   '__builtin_offsetof' '(' typeName ',' unaryExpression ')' #builtInOffsetOf
    ;

genericSelection
    :   '_Generic' '(' assignmentExpression ',' genericAssocList ')'
    ;

genericAssocList
    :   genericAssociation
    |   genericAssocList ',' genericAssociation
    ;

genericAssociation
    :   typeName ':' assignmentExpression
    |   'default' ':' assignmentExpression
    ;

postfixExpression
    :   primaryExpression                                  #PrimExpr
    |   postfixExpression '[' expression ']'               #ArrayCallExpr
    |   postfixExpression '(' argumentExpressionList? ')'  #FunctionCallExpr
    |   postfixExpression '.' Identifier                   #StructCallExpr
    |   postfixExpression '->' Identifier                  #PointerCallExpr
    |   postfixExpression '++'                             #PostPlusPlusExpr
    |   postfixExpression '--'                             #PostMinusMinusExpr
    |   '(' typeName ')' '{' initializerList '}'           #TypeInit1 
    |   '(' typeName ')' '{' initializerList ',' '}'       #TypeInit2
    |   '__extension__' '(' typeName ')' '{' initializerList '}' #Extension1
    |   '__extension__' '(' typeName ')' '{' initializerList ',' '}' #Extension2
    ;

argumentExpressionList
    :   assignmentExpression                               #Argument
    |   argumentExpressionList ',' assignmentExpression    #ListOfArgument
    ;

unaryExpression
    :   postfixExpression                #PostfixExpr
    |   '++' unaryExpression             #PlusPlusExpr
    |   '--' unaryExpression             #MinusMinusExpr
    |   unaryOperator castExpression     #UnaryExpr
    |   'sizeof' unaryExpression         #SizeOfUnaryExpr
    |   'sizeof' '(' typeName ')'        #SizeOfTypeExpr
    |   '_Alignof' '(' typeName ')'      #AlignofExpr
    |   '&&' Identifier                  #AddressGCCExpr // GCC extension address of label 
    ;

unaryOperator
    :   '&' | '*' | '+' | '-' | '~' | '!'
    ;

castExpression
    :   unaryExpression
    |   '(' typeName ')' castExpression
    |   '__extension__' '(' typeName ')' castExpression
    ;

multiplicativeExpression
    :   castExpression                              #MyCastExpression
    |   multiplicativeExpression '*' castExpression #MultiplyExpression
    |   multiplicativeExpression '/' castExpression #DivisionExpression
    |   multiplicativeExpression '%' castExpression #ModuloExpression
    ;

additiveExpression
    :   multiplicativeExpression                        #MyMultiplicationExpression
    |   additiveExpression '+' multiplicativeExpression #PlusExpression
    |   additiveExpression '-' multiplicativeExpression #MinusExpression
    ;

shiftExpression
    :   additiveExpression
    |   shiftExpression '<<' additiveExpression
    |   shiftExpression '>>' additiveExpression
    ;

relationalExpression
    :   shiftExpression                           #ShiftExpr
    |   relationalExpression '<' shiftExpression  #LessExpression
    |   relationalExpression '>' shiftExpression  #GreaterExpression
    |   relationalExpression '<=' shiftExpression #LessOrEqualExpression
    |   relationalExpression '>=' shiftExpression #GreaterOrEqualExpression
    ;

equalityExpression
    :   relationalExpression                         #RelationExpr
    |   equalityExpression '==' relationalExpression #EqualExpression
    |   equalityExpression '!=' relationalExpression #NotEqualExpression
    ;

andExpression
    :   equalityExpression
    |   andExpression '&' equalityExpression
    ;

exclusiveOrExpression
    :   andExpression
    |   exclusiveOrExpression '^' andExpression
    ;

inclusiveOrExpression
    :   exclusiveOrExpression
    |   inclusiveOrExpression '|' exclusiveOrExpression
    ;

logicalAndExpression
    :   inclusiveOrExpression                           #InclusiveOrExpr    
    |   logicalAndExpression '&&' inclusiveOrExpression #AndExpr
    ;

logicalOrExpression
    :   logicalAndExpression                            #LogicalAndExpr
    |   logicalOrExpression '||' logicalAndExpression   #OrExpr
    ;

conditionalExpression
    :   logicalOrExpression ('?' expression ':' conditionalExpression)?
    ;

assignmentExpression
    :   conditionalExpression #condExpr
    |   unaryExpression assignmentOperator assignmentExpression #assignmentExpr 
    ;

assignmentOperator
    :   '=' | '*=' | '/=' | '%=' | '+=' | '-=' | '<<=' | '>>=' | '&=' | '^=' | '|='
    ;

expression
    :   assignmentExpression
    |   expression ',' assignmentExpression
    ;

constantExpression
    :   conditionalExpression
    ;

declaration
    :   declarationSpecifiers initDeclaratorList? ';' #declarationExpr
    |   staticAssertDeclaration #staticAssert
    ;

declarationSpecifiers
    :   declarationSpecifier+
    ;

declarationSpecifiers2
    :   declarationSpecifier+
    ;
 
declarationSpecifier
    :   storageClassSpecifier #storageSpeci
    |   typeSpecifier #typeSpeci
    |   typeQualifier #typeQuali
    |   functionSpecifier #functionSpeci
    |   alignmentSpecifier #alignmentSpeci
    ;

initDeclaratorList
    :   initDeclarator
    |   initDeclaratorList ',' initDeclarator
    ;

initDeclarator
    :   declarator #declaratorDecl
    |   declarator '=' initializer #declareAndInitialize
    ;

storageClassSpecifier
    :   'typedef'
    |   'extern'
    |   'static'
    |   '_Thread_local'
    |   'auto'
    |   'register'
    ;

typeSpecifier
    :   ('void' 
    |   'char'  
    |   'short' 
    |   'int'   
    |   'long'  
    |   'float' 
    |   'double' 
    |   'signed' 
    |   'unsigned' 
    |   '_Bool'    
    |   '_Complex' 
    |   '__m128'   
    |   '__m128d'  
    |   '__m128i') #constantType
    |   '__extension__' '(' ('__m128' | '__m128d' | '__m128i') ')' #extension__
    |   atomicTypeSpecifier #atomicTypeSpeci
    |   structOrUnionSpecifier #strucOrUnionSpeci
    |   enumSpecifier #enumSpeci
    |   typedefName   #typedefNameSpeci
    |   '__typeof__' '(' constantExpression ')' #typeof // GCC extension 
    ;

structOrUnionSpecifier
    :   structOrUnion Identifier? '{' structDeclarationList '}'
    |   structOrUnion Identifier
    ;

structOrUnion
    :   'struct'
    |   'union'
    ;

structDeclarationList
    :   structDeclaration
    |   structDeclarationList structDeclaration
    ;

structDeclaration
    :   specifierQualifierList structDeclaratorList? ';'
    |   staticAssertDeclaration
    ;

specifierQualifierList
    :   typeSpecifier specifierQualifierList?
    |   typeQualifier specifierQualifierList?
    ;

structDeclaratorList
    :   structDeclarator
    |   structDeclaratorList ',' structDeclarator
    ;

structDeclarator
    :   declarator
    |   declarator? ':' constantExpression
    ;

enumSpecifier
    :   'enum' Identifier? '{' enumeratorList '}'
    |   'enum' Identifier? '{' enumeratorList ',' '}'
    |   'enum' Identifier
    ;

enumeratorList
    :   enumerator
    |   enumeratorList ',' enumerator
    ;

enumerator
    :   enumerationConstant
    |   enumerationConstant '=' constantExpression
    ;

enumerationConstant
    :   Identifier
    ;

atomicTypeSpecifier
    :   '_Atomic' '(' typeName ')'
    ;

typeQualifier
    :   'const'
    |   'restrict'
    |   'volatile'
    |   '_Atomic'
    ;

functionSpecifier
    :   ('inline'
    |   '_Noreturn'
    |   '__inline__' // GCC extension
    |   '__stdcall')
    |   gccAttributeSpecifier
    |   '__declspec' '(' Identifier ')'
    ;

alignmentSpecifier
    :   '_Alignas' '(' typeName ')'
    |   '_Alignas' '(' constantExpression ')'
    ;

declarator
    :   pointer? directDeclarator gccDeclaratorExtension*
    ;

directDeclarator
    :   Identifier                                                                    #IdDeclarator
    |   '(' declarator ')'                                                            #ParenthésisDeclarator
    |   directDeclarator '[' typeQualifierList? assignmentExpression? ']'             #BracketDeclarator1
    |   directDeclarator '[' 'static' typeQualifierList? assignmentExpression ']'     #BracketDeclarator2
    |   directDeclarator '[' typeQualifierList 'static' assignmentExpression ']'      #BracketDeclarator3
    |   directDeclarator '[' typeQualifierList? '*' ']'                               #BracketDeclarator4
    |   directDeclarator '(' parameterTypeList ')'                                    #FunctionDeclarator
    |   directDeclarator '(' identifierList? ')'                                      #FunctionDeclarator2
    ;

gccDeclaratorExtension
    :   '__asm' '(' StringLiteral+ ')'
    |   gccAttributeSpecifier
    ;

gccAttributeSpecifier
    :   '__attribute__' '(' '(' gccAttributeList ')' ')'
    ;

gccAttributeList
    :   gccAttribute (',' gccAttribute)*
    |   // empty
    ;

gccAttribute
    :   ~(',' | '(' | ')') // relaxed def for "identifier or reserved word"
        ('(' argumentExpressionList? ')')?
    |   // empty
    ;

nestedParenthesesBlock
    :   (   ~('(' | ')')
        |   '(' nestedParenthesesBlock ')'
        )*
    ;

pointer
    :   '*' typeQualifierList?
    |   '*' typeQualifierList? pointer
    |   '^' typeQualifierList? // Blocks language extension
    |   '^' typeQualifierList? pointer // Blocks language extension
    ;

typeQualifierList
    :   typeQualifier
    |   typeQualifierList typeQualifier
    ;

parameterTypeList
    :   parameterList
    |   parameterList ',' '...'
    ;

parameterList
    :   parameterDeclaration
    |   parameterList ',' parameterDeclaration
    ;

parameterDeclaration
    :   declarationSpecifiers declarator
    |   declarationSpecifiers2 abstractDeclarator?
    ;

identifierList
    :   Identifier
    |   identifierList ',' Identifier
    ;

typeName
    :   specifierQualifierList abstractDeclarator?
    ;

abstractDeclarator
    :   pointer
    |   pointer? directAbstractDeclarator gccDeclaratorExtension*
    ;

directAbstractDeclarator
    :   '(' abstractDeclarator ')' gccDeclaratorExtension*
    |   '[' typeQualifierList? assignmentExpression? ']'
    |   '[' 'static' typeQualifierList? assignmentExpression ']'
    |   '[' typeQualifierList 'static' assignmentExpression ']'
    |   '[' '*' ']'
    |   '(' parameterTypeList? ')' gccDeclaratorExtension*
    |   directAbstractDeclarator '[' typeQualifierList? assignmentExpression? ']'
    |   directAbstractDeclarator '[' 'static' typeQualifierList? assignmentExpression ']'
    |   directAbstractDeclarator '[' typeQualifierList 'static' assignmentExpression ']'
    |   directAbstractDeclarator '[' '*' ']'
    |   directAbstractDeclarator '(' parameterTypeList? ')' gccDeclaratorExtension*
    ;

typedefName
    :   Identifier
    ;

initializer
    :   assignmentExpression
    |   '{' initializerList '}'
    |   '{' initializerList ',' '}'
    ;

initializerList
    :   designation? initializer
    |   initializerList ',' designation? initializer
    ;

designation
    :   designatorList '='
    ;

designatorList
    :   designator
    |   designatorList designator
    ;

designator
    :   '[' constantExpression ']'
    |   '.' Identifier
    ;

staticAssertDeclaration
    :   '_Static_assert' '(' constantExpression ',' StringLiteral+ ')' ';'
    ;

statement
    :   labeledStatement
    |   compoundStatement
    |   expressionStatement
    |   selectionStatement
    |   iterationStatement
    |   jumpStatement
    |   ('__asm' | '__asm__') ('volatile' | '__volatile__') '(' (logicalOrExpression (',' logicalOrExpression)*)? (':' (logicalOrExpression (',' logicalOrExpression)*)?)* ')' ';'
    ;

labeledStatement
    :   Identifier ':' statement #lblstatement
    |   'case' constantExpression ':' statement #case
    |   'default' ':' statement #default
    ;

compoundStatement
    :   '{' blockItemList? '}'
    ;

blockItemList
    :   blockItem
    |   blockItemList blockItem
    ;

blockItem
    :   statement
    |   declaration
    ;

expressionStatement
    :   expression? ';'
    ;

selectionStatement
    :   'if' '(' expression ')' statement ('else' statement)? #if
    |   'switch' '(' expression ')' statement #switch
    ;

iterationStatement
    :   'while' '(' expression ')' statement #while
    |   'do' statement 'while' '(' expression ')' ';' #do
    |   'for' '(' expression? ';' expression? ';' expression? ')' statement #for
    |   'for' '(' declaration expression? ';' expression? ')' statement #for_alt
    ;

jumpStatement
    :   'goto' Identifier ';' #goto
    |   'continue' ';'		  #continue
    |   'break' ';'			  #break
    |   'return' expression? ';' #return
    |   'goto' unaryExpression ';' #gotoUnary // GCC extension
    ;

compilationUnit
    :   translationUnit? EOF
    ;

translationUnit
    :   externalDeclaration
    |   translationUnit externalDeclaration
    ;

externalDeclaration
    :   functionDefinition
    |   declaration
    |   ';' // stray ;
    ;

functionDefinition
    :   declarationSpecifiers? declarator declarationList? compoundStatement
    ;

declarationList
    :   declaration
    |   declarationList declaration
    ;

Auto : 'auto';
Break : 'break';
Case : 'case';
Char : 'char';
Const : 'const';
Continue : 'continue';
Default : 'default';
Do : 'do';
Double : 'double';
Else : 'else';
Enum : 'enum';
Extern : 'extern';
Float : 'float';
For : 'for';
Goto : 'goto';
If : 'if';
Inline : 'inline';
Int : 'int';
Long : 'long';
Register : 'register';
Restrict : 'restrict';
Return : 'return';
Short : 'short';
Signed : 'signed';
Sizeof : 'sizeof';
Static : 'static';
Struct : 'struct';
Switch : 'switch';
Typedef : 'typedef';
Union : 'union';
Unsigned : 'unsigned';
Void : 'void';
Volatile : 'volatile';
While : 'while';

Alignas : '_Alignas';
Alignof : '_Alignof';
Atomic : '_Atomic';
Bool : '_Bool';
Complex : '_Complex';
Generic : '_Generic';
Imaginary : '_Imaginary';
Noreturn : '_Noreturn';
StaticAssert : '_Static_assert';
ThreadLocal : '_Thread_local';

LeftParen : '(';
RightParen : ')';
LeftBracket : '[';
RightBracket : ']';
LeftBrace : '{';
RightBrace : '}';

Less : '<';
LessEqual : '<=';
Greater : '>';
GreaterEqual : '>=';
LeftShift : '<<';
RightShift : '>>';

Plus : '+';
PlusPlus : '++';
Minus : '-';
MinusMinus : '--';
Star : '*';
Div : '/';
Mod : '%';

And : '&';
Or : '|';
AndAnd : '&&';
OrOr : '||';
Caret : '^';
Not : '!';
Tilde : '~';

Question : '?';
Colon : ':';
Semi : ';';
Comma : ',';

Assign : '=';
// '*=' | '/=' | '%=' | '+=' | '-=' | '<<=' | '>>=' | '&=' | '^=' | '|='
StarAssign : '*=';
DivAssign : '/=';
ModAssign : '%=';
PlusAssign : '+=';
MinusAssign : '-=';
LeftShiftAssign : '<<=';
RightShiftAssign : '>>=';
AndAssign : '&=';
XorAssign : '^=';
OrAssign : '|=';

Equal : '==';
NotEqual : '!=';

Arrow : '->';
Dot : '.';
Ellipsis : '...';

Identifier
    :   IdentifierNondigit
        (   IdentifierNondigit
        |   Digit
        )*
    ;

fragment
IdentifierNondigit
    :   Nondigit
    |   UniversalCharacterName
    //|   // other implementation-defined characters...
    ;

fragment
Nondigit
    :   [a-zA-Z_]
    ;

fragment
Digit
    :   [0-9]
    ;

fragment
UniversalCharacterName
    :   '\\u' HexQuad
    |   '\\U' HexQuad HexQuad
    ;

fragment
HexQuad
    :   HexadecimalDigit HexadecimalDigit HexadecimalDigit HexadecimalDigit
    ;

Constant
    :   IntegerConstant
    |   FloatingConstant
    //|   EnumerationConstant
    |   CharacterConstant
    ;

fragment
IntegerConstant
    :   DecimalConstant IntegerSuffix?
    |   OctalConstant IntegerSuffix?
    |   HexadecimalConstant IntegerSuffix?
    ;

fragment
DecimalConstant
    :   NonzeroDigit Digit*
    ;

fragment
OctalConstant
    :   '0' OctalDigit*
    ;

fragment
HexadecimalConstant
    :   HexadecimalPrefix HexadecimalDigit+
    ;

fragment
HexadecimalPrefix
    :   '0' [xX]
    ;

fragment
NonzeroDigit
    :   [1-9]
    ;

fragment
OctalDigit
    :   [0-7]
    ;

fragment
HexadecimalDigit
    :   [0-9a-fA-F]
    ;

fragment
IntegerSuffix
    :   UnsignedSuffix LongSuffix?
    |   UnsignedSuffix LongLongSuffix
    |   LongSuffix UnsignedSuffix?
    |   LongLongSuffix UnsignedSuffix?
    ;

fragment
UnsignedSuffix
    :   [uU]
    ;

fragment
LongSuffix
    :   [lL]
    ;

fragment
LongLongSuffix
    :   'll' | 'LL'
    ;

fragment
FloatingConstant
    :   DecimalFloatingConstant
    |   HexadecimalFloatingConstant
    ;

fragment
DecimalFloatingConstant
    :   FractionalConstant ExponentPart? FloatingSuffix?
    |   DigitSequence ExponentPart FloatingSuffix?
    ;

fragment
HexadecimalFloatingConstant
    :   HexadecimalPrefix HexadecimalFractionalConstant BinaryExponentPart FloatingSuffix?
    |   HexadecimalPrefix HexadecimalDigitSequence BinaryExponentPart FloatingSuffix?
    ;

fragment
FractionalConstant
    :   DigitSequence? '.' DigitSequence
    |   DigitSequence '.'
    ;

fragment
ExponentPart
    :   'e' Sign? DigitSequence
    |   'E' Sign? DigitSequence
    ;

fragment
Sign
    :   '+' | '-'
    ;

fragment
DigitSequence
    :   Digit+
    ;

fragment
HexadecimalFractionalConstant
    :   HexadecimalDigitSequence? '.' HexadecimalDigitSequence
    |   HexadecimalDigitSequence '.'
    ;

fragment
BinaryExponentPart
    :   'p' Sign? DigitSequence
    |   'P' Sign? DigitSequence
    ;

fragment
HexadecimalDigitSequence
    :   HexadecimalDigit+
    ;

fragment
FloatingSuffix
    :   'f' | 'l' | 'F' | 'L'
    ;

fragment
CharacterConstant
    :   '\'' CCharSequence '\''
    |   'L\'' CCharSequence '\''
    |   'u\'' CCharSequence '\''
    |   'U\'' CCharSequence '\''
    ;

fragment
CCharSequence
    :   CChar+
    ;

fragment
CChar
    :   ~['\\\r\n]
    |   EscapeSequence
    ;

fragment
EscapeSequence
    :   SimpleEscapeSequence
    |   OctalEscapeSequence
    |   HexadecimalEscapeSequence
    |   UniversalCharacterName
    ;

fragment
SimpleEscapeSequence
    :   '\\' ['"?abfnrtv\\]
    ;

fragment
OctalEscapeSequence
    :   '\\' OctalDigit
    |   '\\' OctalDigit OctalDigit
    |   '\\' OctalDigit OctalDigit OctalDigit
    ;

fragment
HexadecimalEscapeSequence
    :   '\\x' HexadecimalDigit+
    ;

StringLiteral
    :   EncodingPrefix? '"' SCharSequence? '"'
    ;

fragment
EncodingPrefix
    :   'u8'
    |   'u'
    |   'U'
    |   'L'
    ;

fragment
SCharSequence
    :   SChar+
    ;

fragment
SChar
    :   ~["\\\r\n]
    |   EscapeSequence
    ;

LineDirective
    :   '#' Whitespace? DecimalConstant Whitespace? StringLiteral ~[\r\n]*
        -> skip
    ;

PragmaDirective
    :   '#' Whitespace? 'pragma' Whitespace ~[\r\n]*
        -> skip
    ;

Whitespace
    :   [ \t]+
        -> skip
    ;

Newline
    :   (   '\r' '\n'?
        |   '\n'
        )
        -> skip
    ;

BlockComment
    :   '/*' .*? '*/'
        -> skip
    ;

LineComment
    :   '//' ~[\r\n]*
        -> skip
    ;