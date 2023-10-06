import argparse
ListaTokens =[]



def automataNumeros (linea,cadenas,ListaTokens)
    alfa=["1","2","3","4","5","6","7","8","9"]
    TC=["0",["1","2","3","4","5","6","7","8","9","0"],"15"],
       ["15",["1","2","3","4","5","6","7","8","9","0"],"15"],
       ["15",["."],"16"],
       ["15",["E"],"16"],
       ["16",["1","2","3","4","5","6","7","8","9","0"],"17"],
       ["17",["1","2","3","4","5","6","7","8","9","0"],"17"],
       ["17",["E"],"18"],
       ["18",["+","-"],"19"],
       ["18",["1","2","3","4","5","6","7","8","9","0"],"20"],
       ["18",["1","2","3","4","5","6","7","8","9","0"],"20"],
       ["20",["1","2","3","4","5","6","7","8","9","0"],"20"]
    EI="0"
    EA=EA
    EF=["15","17","20"]
    B=True
    cont=0
    lex=""

    for caracter in cadena:
        if caracter == " ":
            cont = cont + 1
        else:
            if caracterin alfa:
                if caracter == "+" or caracter == "-":
                    if EA = 19:
                        lex+lex+caracter
                        cont=cont+1
                        for f in TC:
                            if caracter in f[2] adn EA in f[0]:
                                TC.append([EA,caracter,f[2]])
                                EA=f[2]
                                breal
                    else:
                        if!="":
                            ListaTokens.append("NUMBER")
                            ListaTokens.append(lex)
                            num=float(lex)
                            ListaTokens.append(num)
                            reconoceUnToken(linea,cadena[cont:lent(cadena)], ListaTokens)
                            return 0
                        else:
                            reconoceUnToken(linea,cadena[cont:lent(cadena)], ListaTokens)
                            return 0
                else:
                    lex=lex+caracter
                    cont=cont+1
                    for f in TC:
                        if caracter in f[1] and EA in f[0]:
                            TC.append([EA,caracter,f[2]])
                            EA=f[2]
                            break

        else: #POR SI NO ESTA EN EL ALFABETO
            if EA in EI:
                if lex != "":
                    ListaTokens.append("NUMBER")
                    ListaTokens.append(lex)
                    num=float(lex) 
                    ListaTokens.append(num)
                    reconoceUnToken(linea,cadena[cont:len(cadena)],ListaTokens)
                    return 0
                else:
                    reconoceUnToken(linea,cadena[cont:len(cadena)],ListaTokens)
                    return 0
            else: #Por si no encuentra ningun numero
            if cadena [cont:len(cadena)]:
                reconoceUnToken(linea,cadena[cont:len(cadena)],ListaTokens)
                return 0
            else:
                return 0

    if EA in EF:
        ListaTokens.append ("NUMBER")
        ListaTokens.append(lex)
        num=float(lex)
        ListaTokens.append(lex)
        if cadena[cont:len(Cadena)]:
            reconoceUnToken(linea,cadena[cont:len(cadena)],ListaTokens)
        else: #CUANDO YA NO HAY CADENAS QUE LEER
            return 0

    else:
        print(f"ERROR en linea{linea}")
        if cadena[cont:len(cadena)]:
            reconoceUnToken(linea,cadena[cont:len(cadena)],ListaTokens)
        else:
            return 0

    #Falta Revisar posible errores                 
        
def reconoceUnToken (linea,cadena,ListaTokens):
    TokensUnSoloCarac={
        "(":"LEFT_PAREN",
        ")":"RIGHT_PAREN",
        "{":"LEFT_BRACE",
        "}":"RIGHT_BRACE",
        ",":"COMMA",
        ".":"DOT",
        "-":"MINUS",
        "+":"PLUS",
        ";":"SEMICOLON",
        "*":"STAR"
    }

    cont=0
    for i in cadena:
        if i == " ":
            cont=cont+cont
        else:
            if i in TokensUnSoloCarac:
                cont=comt+1
                valor=TokensUnSoloCarac[i]
                ListaTokens.append(valor)
                ListaTokens.append(i)
                ListaTokens.append("")
            else:
                automataOperadores(linea,cadena[cont:len(cadena)],ListaTokens)
                return 0
            else
                return 0

#NOTA:  BORREN EL COMENTARIO YA QUE PONGAN SU AUTÓMATA

#automata cadenas

#esPalabra reservada

#automata palabras reservadas
def automataPalabrasReserv(linea, cadena, ListaTokens):
    EI = '0'
    EA = EI
    lex = ''
    cont = 0
    for caracter in cadena
        if caracter == ' ':
            cont = cont + 1
        else:
            if EA == '0':
                if caracter.isalpha():
                    lex = lex + caracter
                    cont = cont + 1
                    EA = '13'
                else:
                    if cadena[cont:len(cadena)]:
                        automataCadenas(linea, cadena[cont:len(cadena)], ListaTokens)
                        return 0
                    else:
                        return 0
            else:
                if EA == '13':
                    if caracter.isalpha() or caracter.isdigit():
                        cont = cont + 1
                        lex = lex + caracter
                    else:
                        if EsPaRes(lex):
                            ListaTokens.append(lex.upper())
                            ListaTokens.append(lex)
                            ListaTokens.append('')
                        else:
                            ListaTokens.append('IDENTIFIER')
                            ListaTokens.append(lex)
                            ListaTokens.append('')

                        if cadena[cont:len(cadena)]:
                            automataCadenas(linea, cadena[cont:len(cadena)], ListaTokens)
                            return 0
                        else:
                            return 0

    if EA == '13':
        if lex != '':
            if EsPaRes(lex):
                ListaTokens.append(lex.upper())
                ListaTokens.append(lex)
                ListaTokens.append('')
            else:
                ListaTokens.append('IDENTIFIER')
                ListaTokens.append(lex)
                ListaTokens.append('')
        if cadena[cont:len(cadena)]:
            automataCadenas(linea, cadena[cont:len(cadena)], ListaTokens)
            return 0
        else:
            return 0


def automataOperadores(linea, cadena, ListaTokens):
    alfa = ['>', '<', '=', '!']
    TC = [['0', ['>'], '1'],
             ['1', ['='], '2'],
             ['0', ['<'], '4'],
             ['4', ['='], '5'],
             ['0', ['='], '7'],
             ['7', ['='], '8'],
             ['0', ['!'], '10'],
             ['10', ['='], '11']]
    b = True
    EI = '0'
    EA = EI
    EF = ['2', '8', '11', '5', '1', '7', '4', '10']
    cont = 0
    lex = ''

    for caracter in cadena:
        if caracter == ' ':
            cont = cont + 1
        else:
            if caracter in alfa:
                cont = cont + 1
                lex = lex + caracter
                for f in TC:
                    if caracter in f[1] and EA in f[0]:
                        TC.append([EA, caracter, f[2]])
                        EA = f[2]
                        break
            else: #Analizamos que pasa si recibe algo que no está en el alfabeto
                if EA == '1':
                    ListaTokens.append('GREATER')
                    ListaTokens.append(lex)
                    ListaTokens.append('')
                    automataPalabrasReserv(linea, cadena[cont:len(cadena)], ListaTokens)
                    return 0
                else:
                    if EA == '4':
                        ListaTokens.append('LESS')
                        ListaTokens.append(lex)
                        ListaTokens.append('')
                        automataPalabrasReserv(linea, cadena[cont:len(cadena)], ListaTokens)
                        return 0
                    else:
                        if EA == '7':
                            ListaTokens.append('EQ')
                            ListaTokens.append(lex)
                            ListaTokens.append('')
                            automataPalabrasReserv(linea, cadena[cont:len(cadena)], ListaTokens)
                            return 0
                        else:
                            if EA == '10':
                                ListaTokens.append('BANG')
                                ListaTokens.append(lex)
                                ListaTokens.append('')
                                automataPalabrasReserv(linea, cadena[cont:len(cadena)], ListaTokens)
                                return 0
                            else:
                                if EA == '2':
                                    ListaTokens.append('GREATER_EQUAL')
                                    ListaTokens.append(lex)
                                    ListaTokens.append('')
                                    automataPalabrasReserv(linea, cadena[cont:len(cadena)], ListaTokens)
                                    return 0
                                else:
                                    if EA == '5':
                                        ListaTokens.append('LESS_EQUAL')
                                        ListaTokens.append(lex)
                                        ListaTokens.append('')
                                        automataPalabrasReserv(linea, cadena[cont:len(cadena)], ListaTokens)
                                        return 0
                                    else:
                                        if EA == '8':
                                            ListaTokens.append('EQUAL_EQUAL')
                                            ListaTokens.append(lex)
                                            ListaTokens.append('')
                                            automataPalabrasReserv(linea, cadena[cont:len(cadena)], ListaTokens)
                                            return 0
                                        else:
                                            if EA == '11':
                                                ListaTokens.append('BANG_EQUAL')
                                                ListaTokens.append(lex)
                                                ListaTokens.append('')
                                                automataPalabrasReserv(linea, cadena[cont:len(cadena)], ListaTokens)
                                                return 0
                                            else:
                                                if cadena[cont:len(cadena)]:
                                                    automataPalabrasReserv(linea, cadena[cont:len(cadena)], ListaTokens)
                                                    return 0
                                                else:
                                                    return 0


    if EA in EF:

        if EA == '2':
            ListaTokens.append('GREATER_EQUAL')
            ListaTokens.append(lex)
            ListaTokens('')
        else:
            if EA=='5':
                ListaTokens.append('LESS_EQUAL')
                ListaTokens.append(lex)
                ListaTokens.append('')
            else:
                if EA == '8':
                    ListaTokens.append('EQUAL_EQUAL')
                    ListaTokens.append(lex)
                    ListaTokens.append('')
                else:
                    if EA == '11':
                        ListaTokens.append('BANG_EQUAL')
                        ListaTokens.append(lex)
                        ListaTokens.append('')
                    else:
                        if EA == '1':
                            ListaTokens.append('GREATER')
                            ListaTokens.append(lex)
                            ListaTokens.append('')
                        else:
                            if EA == '7':
                                ListaTokens.append('EQ')
                                ListaTokens.append(lex)
                                ListaTokens.append('')
                            else:
                                if EA == '4':
                                    ListaTokens.append('LESS')
                                    ListaTokens.append(lex)
                                    ListaTokens.append('')
                                else:
                                    ListaTokens.append('BANG')
                                    ListaTokens.append(lex)
                                    ListaTokens.append('')

        if cadena[cont:len(cadena)]:
            automataPalabrasReserv(linea, cadena[cont:len(cadena)], ListaTokens)
            return 0
        else:
            return 0

    else:
        if cadena[cont:len(cadena)]:
            automataPalabrasReserv(linea, cadena[cont:len(cadena)], ListaTokens)
            return 0
        else:
            return 0

#reconoce token (moverlo aqui)

#automata numeros (moverlo aqui)

def automataComentarios(linea, cadena, ListaTokens):
    alfa = ['/', '*', '\n']
    TC = [['0', ['/'], '26'],
          ['0', ['\n'], '0'],
          ['26', ['*'], '27'],
          ['27', ['*'], '28'],
          ['28', ['*'], '28'],
          ['28', ['/'], '29'],
          ['26', ['/'], '30'],
          ['30', ['\n'], '0'],
          ['29', ['\n'], '0']
          ]
    EI = '0'
    EA = EI
    EF = ['29', '31', '30', '26', '0']
    cont = 0
    lex = ''

    for caracter in cadena:
        if cadena != '':
            if caracter == ' ':
                cont = cont + 1
            else:
                if caracter == '*' and EA == '0':
                    if cadena[cont:len(cadena)]:
                        automataNumeros(linea, cadena[cont:len(cadena)], ListaTokens)
                        return 0
                    else:
                        return 0
                else:
                    if caracter in alfa:
                        if caracter == '\n':
                            linea = linea + 1
                            cont = cont + 1

                            if EA == '0':
                                automataComentarios(linea, cadena[cont:len(cadena)], ListaTokens)
                                return 0
                            else:
                                for f in TC:
                                    if caracter in f[1] and (EA in f[0]):
                                        EA = f[2]
                                        break

                        else:
                            cont = cont + 1
                            for f in TC:
                                if caracter in f[1] and (EA in f[0]):
                                    EA = f[2]
                                    break

                    else:
                        if EA == '27':
                            cont = cont + 1
                        else:
                            if EA == '28':
                                EA = '27'
                                cont = cont + 1
                            else:
                                if EA == '30':
                                    cont = cont + 1
                                else:
                                    if EA == '26':
                                        ListaTokens.append('SLASH')
                                        ListaTokens.append('/')
                                        ListaTokens.append('')
                                        if cadena[cont:len(cadena)]:
                                           automataNumeros(linea, cadena[cont:len(cadena)], ListaTokens)
                                           return 0
                                        else:
                                            return 0
                                    else:
                                        if cadena[cont:len(cadena)]:
                                            automataNumeros(linea, cadena[cont:len(cadena)], ListaTokens)
                                            return 0
                                        else:
                                            return 0

        else:
            return 0

    if EA in EF:
        print(EA)
        if EA == '27':
            ListaTokens.append('SLASH')
            ListaTokens.append('/')
            ListaTokens.append('')
            if cadena[cont:len(cadena)]:
                automataNumeros(linea, cadena[cont:len(cadena)], ListaTokens)
                return 0
            else:
                return 0
        else:
            if cadena[cont:len(cadena)]:
                automataNumeros(linea, cadena[cont:len(cadena)], ListaTokens)
                return 0
            else:
                return 0

    else:
        print(f'Error en linea: {linea}')
        if cadena[cont:len(cadena)]:
            automataNumeros(linea, cadena[cont:len(cadena)], ListaTokens)
            return 0
        else:
            return 0
def main():
    ListaTokens=[]
    linea=1
    parser = argparse.ArgumentParser(description="")
    parser.add_argument("archivo", nargs="?", help="Archivo (opcional) de entrada")

    args = parser.parse_args()

    if args.archivo:
        with open(args.archivo, "r") as archivo:  #lee el archivo proporcionado
            lista_caracteres =[]

0            caracter = archivo.read(1)
            while caracter:
                lista_caracteres.append(caracter)
                caracter = archivo.read(1)

            cadenaCompleta = ''.join(lista_caracteres)
            automataComentarios(linea, cadenaCompleta, ListaTokens)


        for i in range(0,len(ListaTokens),3):
            grupo = ListaTokens[i:i+3]
            print(grupo)


    else:
        while True: #solicita las entradas repetidamente
            ListaTokens=[]
            entrada = input("")
            l = 1
            if entrada:
                s = automataComentarios(linea, entrada, ListaTokens)


            else:
                break

            for i in range(0, len(ListaTokens), 3):
                grupo = ListaTokens[i:i + 3]


if __name__ == "__main__":
    main()
