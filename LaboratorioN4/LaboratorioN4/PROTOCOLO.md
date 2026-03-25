# PROTOCOLO RFC CLIENTE-SERVIDOR - CONVERSOR NUMÉRICO

## Especificación del Protocolo

### Configuración de Red
- **Puerto**: 12345
- **Host**: localhost
- **Protocolo**: TCP/IP
- **Separador**: ";" (punto y coma)
- **Codificación**: UTF-8

### Formato de Mensajes

#### Solicitudes del Cliente
```
OPERACION;PARAMETRO1;PARAMETRO2
```

#### Respuestas del Servidor
```
ESTADO;RESULTADO
```

### Operaciones Disponibles

| Código | Operación | Formato Solicitud | Ejemplo |
|--------|-----------|-------------------|----------|
| 1 | Decimal a Binario | `1;numero;bits` | `1;10;16` |
| 2 | Binario a Decimal | `2;binario` | `2;110110` |
| 3 | Decimal a Hexadecimal | `3;numero;digitos` | `3;271;4` |
| 4 | Hexadecimal a Decimal | `4;hexadecimal` | `4;3E1` |
| 5 | Binario a Hexadecimal | `5;binario;digitos` | `5;101000011011;4` |
| 6 | Hexadecimal a Binario | `6;hexadecimal` | `6;5B1A` |
| 0 | Desconexión | `0;desconectar` | `0;desconectar` |

### Estados de Respuesta

- **OK**: Operación exitosa
- **ERROR**: Error en la operación

### Ejemplos de Comunicación

#### Conversión Decimal a Binario
```
Cliente → Servidor: 1;10;16
Servidor → Cliente: OK;0000000000001010
```

#### Conversión con Error
```
Cliente → Servidor: 1;abc;16
Servidor → Cliente: ERROR;Formato numérico inválido
```

#### Desconexión
```
Cliente → Servidor: 0;desconectar
Servidor → Cliente: OK;Desconexión
```

### Manejo de Errores

- `ERROR;Solicitud vacía`: Mensaje vacío o nulo
- `ERROR;Formato inválido`: Estructura de mensaje incorrecta
- `ERROR;Parámetros insuficientes`: Faltan parámetros requeridos
- `ERROR;Formato numérico inválido`: Número en formato incorrecto
- `ERROR;Operación no válida`: Código de operación desconocido

### Características del Protocolo

1. **Conexión Persistente**: El cliente mantiene la conexión abierta
2. **Múltiples Solicitudes**: Se pueden enviar varias operaciones por sesión
3. **Manejo Robusto de Errores**: Validación completa de entrada
4. **Desconexión Controlada**: Comando específico para terminar sesión
5. **Concurrencia**: El servidor maneja múltiples clientes simultáneamente