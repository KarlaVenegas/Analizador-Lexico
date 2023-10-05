import argparse
ListaTokens =[]

def automataComentarios(linea, cadena, ListaTokens):
    alfa = ['/', '*', '\n']
    TC = [['0', ['/'], '26'],
          ['0', ['\n'], '0'],
          ['26', ['*'], '27'],
          ['27', ['*'], '28'],
          ['28', ['*'], '28'],
          ['28', ['/'], '29'],
          ['26', ['/'], '30'],
          ['30', ['\n'], '31'],
          ['29', ['\n'], '0']
          ]
    EI = '0'
    EA = EI
    EF = ['29', '31', '30', '26', '0']
    bandera = True
    cont = 0
    lex = ''

    for caracter in cadena:
        if cadena != '':
            if caracter == ' ':
                cont = cont + 1
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

    if EA in EF and (bandera == True):
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
    parser = argparse.ArgumentParser(description="Ejemplo o programa con interfaz de linea de comandos")
    parser.add_argument("archivo", nargs="?", help="Archivo (opcional) de entrada")

    args = parser.parse_args()

    if args.archivo:
        with open(args.archivo, "r") as archivo:  #lee el archivo proporcionado
            lista_caracteres =[]

            caracter = archivo.read(1)
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