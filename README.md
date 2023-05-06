# OpenAPIGenerator

The generator uses [K-OpenApi-Parser](https://github.com/Qawaz/K-OpenApi-Parser) and [__KATE
__](https://github.com/Qawaz/KATE) to
generate client and server code for different languages

## Example Output

You can checkout generated code inside the [output](./output) directory for each language

## Usage

Main file takes three command line parameters

1 - language-gen-type (type of generation) [Supported Generations](#Supported)

2 - schema_path (path to the schema file)

3 - output_dir_path (output directory path)

## Customization

When you generate for the first , In the output folder the generator generates a KATE configuration file
Where you can set KATE configuration parameters that will be read and used the next time you generate
If you'd like to generate with default configuration , delete the KATE configuration file

This offers global configuration like

Golang Package Name and we plan to support more configuration since its so easy when using KATE

This configuration is basically available inside KATE templates and is accessible through config object

### More Customization

If you'd like to customize kotlin models , add annotations on them or change how the code works

You'd need to override the template , I'd say better make a fork of this repo and see how templates are written

This would be very easy since KATE placeholders are used , You just need to

You can also open an issue for it , I'd love to support that customization using placeholders
so further customization is easier

## Supported

| Language Generation Type     | Command Line Parameter | Supported |
|------------------------------|------------------------|-----------|
| Golang Models (Struct)       | golang-models          | &check;   |
| Golang Server (uses gorilla) | golang-server          | &check;   |
| HTML                         | html                   | &check;   |
| Kotlin Models Only           | kotlin                 | &check;   |
| JSON (Models)                | json                   | &check;   |
| Raw                          | raw                    | &check;   |
| Kotlin Ktor Client           | kotlin-ktor-client     | TODO      |
| Markdown                     | markdown-docs          | &cross;   |

## Licensing

Any code you generate using the generator or templates that you created are yours and you can license them however you
like.
The code in this repo and its templates are licensed under the repo [LICENSE](./LICENSE)