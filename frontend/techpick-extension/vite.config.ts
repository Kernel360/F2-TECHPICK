import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';
import { resolve } from 'path';
import { viteStaticCopy } from 'vite-plugin-static-copy';
import { vanillaExtractPlugin } from '@vanilla-extract/vite-plugin';
import tsconfigPaths from 'vite-tsconfig-paths';

// https://vitejs.dev/config/
export default defineConfig({
  build: {
    rollupOptions: {
      input: {
        background: resolve(__dirname, 'src/chrome-extension/background.ts'),
        popup: resolve(__dirname, './index.html'),
        contentscript: resolve(
          __dirname,
          './src/chrome-extension/contentscript.ts'
        ),
      },
      output: {
        entryFileNames: '[name].js',
      },
    },
    outDir: 'dist',
  },
  plugins: [
    react(),
    viteStaticCopy({
      targets: [{ src: './src/chrome-extension/manifest.json', dest: '.' }],
    }),
    vanillaExtractPlugin(),
    tsconfigPaths(),
  ],
});
